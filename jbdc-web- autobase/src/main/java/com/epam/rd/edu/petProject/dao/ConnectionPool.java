package com.epam.rd.edu.petProject.dao;

import com.epam.rd.edu.petProject.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public final class ConnectionPool {
    private static ConnectionPool pool = null;
    private static final Logger log = LoggerFactory.getLogger(Connection.class);
    private final Queue<ConnectionDateWrapper> free = new ConcurrentLinkedDeque<>();
    private static int timeCloseMinutes = 2;
    private final ArrayList<ThreadForClean> threadsForClean = new ArrayList<>();

    private ConnectionPool() {
    }

    public static ConnectionPool getConnectionPool() {
        if (pool == null) {
            pool = new ConnectionPool();
        }

        return pool;
    }

    public ConnectionDateWrapper getConnection() {
        ConnectionDateWrapper connectionDateWrapper;

        if (free.peek() != null) {
            connectionDateWrapper = free.poll();
        } else {
            connectionDateWrapper = new ConnectionDateWrapper();
            log.info("Created new connectionDateWrapper");
        }
        checkNecessityOfCleanThread();

        return connectionDateWrapper;
    }

    private void checkNecessityOfCleanThread() {
        if (threadsForClean.isEmpty()) {
            ThreadForClean threadForClean = new ThreadForClean("ConnectionCleaner");
            threadsForClean.add(threadForClean);

            threadForClean.start();
        }
    }

    public static void setTimeCloseMs(int timeCloseMs) {
        ConnectionPool.timeCloseMinutes = timeCloseMs;
    }

    public static int getTimeCloseMs() {
        return timeCloseMinutes;
    }

    public final class ConnectionDateWrapper implements AutoCloseable {
        private Connection connection;
        private LocalDateTime date;

        private ConnectionDateWrapper() {
            try {
                connection = getNewConnection();
                date = LocalDateTime.now();
            } catch (SQLException ex) {
                throw new DaoException(ex);
            }
        }

        public Connection getConnection() {
            return connection;
        }

        public LocalDateTime getDate() {
            return date;
        }

        private java.sql.Connection getNewConnection() throws SQLException {
            Properties props = getProps();
            String drivers = props.getProperty("db.driver");
            if (drivers != null) {
                System.setProperty("db.driver", drivers);
            }
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            try {
                Class.forName(props.getProperty("db.driver"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection(url, username, password);
        }

        private Properties getProps() {
            Properties props = new Properties();
            try (InputStream in =
                         ConnectionDateWrapper.class.getClassLoader().getResourceAsStream("db.properties")) {
                props.load(in);
            } catch (IOException ex) {
                String msg = "Error getting db.properties file" + System.lineSeparator() + ex.getMessage();
                throw new DaoException(msg, ex);
            }

            return props;
        }

        @Override
        public void close() {
            free.add(this);
        }
    }

    private final class ThreadForClean extends Thread {

        public ThreadForClean(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!free.isEmpty()) {
                deleteUnusedConnections();
                try {
                    sleep(2 * 60 * 1000 + 1000);
                } catch (InterruptedException e) {
                    log.error(String.format("%s : InterruptedException" + System.lineSeparator()
                            + e.getMessage(), this.getName()));
                }
            }
            synchronized (threadsForClean) {
                threadsForClean.remove(this);
            }
        }

        private void deleteUnusedConnections() {
            Iterator iter = free.iterator();

            synchronized (free) {
                while (iter.hasNext()) {
                    ConnectionDateWrapper connectionDateWrapper = (ConnectionDateWrapper) iter.next();
                    if ((connectionDateWrapper.getDate().getMinute() + timeCloseMinutes) < LocalDateTime.now().getMinute()) {
                        iter.remove();
                        log.info("Removed new connectionDateWrapper");
                    }
                }
            }
        }
    }
}
