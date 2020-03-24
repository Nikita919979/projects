package com.epam.rd.edu.petProject.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocaleFilter implements Filter {
    private final Map<String, Locale> localeHashMap;
    private static final Logger log = LoggerFactory.getLogger(LocaleFilter.class);


    public LocaleFilter() {
        localeHashMap = new HashMap<>();
        localeHashMap.put("en", new Locale("en", "EN"));
        localeHashMap.put("ru", new Locale("ru", "RU"));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setCharacterEncoding("UTF-8");

        if (session.getAttribute("bundleFile") == null) {
            session.setAttribute("bundleFile", "localization.locale");
        }

        Locale locale;
        Locale sessionLocale = (Locale) session.getAttribute("locale");
        String userLocale = httpRequest.getParameter("language");
        if (sessionLocale != null && userLocale == null) {
            userLocale = sessionLocale.getLanguage();
        }

        if (sessionLocale == null) {
            locale = getDefaultLocale();
        } else if (!userLocale.equals(sessionLocale.getLanguage())) {
            locale = getCertainLocale(userLocale);
        } else {
            locale = sessionLocale;
        }
        httpRequest.setAttribute("locale", locale);
        session.setAttribute("locale", locale);
        log.error("locale change");
        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }

    private Locale getCertainLocale(String userLocale) {
        return localeHashMap.get(userLocale);
    }

    private Locale getDefaultLocale() {
        return localeHashMap.get("en");
    }
}
