package com.epam.rd.edu.petproject.utils.datagenerator;

import com.epam.rd.edu.petproject.converter.CityConverter;
import com.epam.rd.edu.petproject.converter.OrderConverter;
import com.epam.rd.edu.petproject.converter.UserConverter;
import com.epam.rd.edu.petproject.dto.CityDto;
import com.epam.rd.edu.petproject.dto.OrderDto;
import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.model.Car.CarModel;
import com.epam.rd.edu.petproject.model.Order;
import com.epam.rd.edu.petproject.model.User.Role;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestOrderDataGenerator {

  private final CityConverter cityConverter = new CityConverter();
  private final UserConverter userConverter = new UserConverter();
  private final OrderConverter orderConverter = new OrderConverter(userConverter, cityConverter);

  public List<OrderDto> generateRequestOrderDtoList(int count) {
    return IntStream.range(0, count)
        .mapToObj(TestOrderDataGenerator::generateOrderDto)
        .collect(Collectors.toList());
  }

  public OrderDto generateOrderDto(int counter) {
    return OrderDto.builder()
        .uuid(UUID.randomUUID())
        .carModel(Arrays.stream(CarModel.values()).findAny().get())
        .city_from(TestCityDataGenerator.generateCityDto(counter))
        .city_to(TestCityDataGenerator.generateCityDto(counter))
        .user(TestUserDataGenerator.generateUserDtoWithRandomRole(counter))
        .build();
  }

  public Order getOrder(OrderDto orderDto) {
    return orderConverter.toEntity(orderDto);
  }

  public List<Order> getOrderList(List<OrderDto> orderDtoList) {
    return orderConverter.toEntityList(orderDtoList);
  }

  public OrderDto generateRealEntity(int counter) {
    UserDto userDto = UserDto.builder()
        .email("nikita919979@gmail.com")
        .familyName("Poddubskiy")
        .uuid(UUID.fromString("df9e5624-71db-11ea-bc55-0242ac130003"))
        .login("admin")
        .role(Role.ADMIN)
        .name("Nikita")
        .build();

    CityDto city1 = CityDto.builder()
        .name("Dnepr")
        .uuid(UUID.fromString("caad8f82-71db-11ea-bc55-0242ac130003"))
        .build();

    CityDto city2 = CityDto.builder()
        .name("Rome")
        .uuid(UUID.fromString("cfd3fb4a-71db-11ea-bc55-0242ac130003"))
        .build();

    return OrderDto.builder()
        .user(userDto)
        .city_to(city1)
        .city_from(city2)
        .carModel(CarModel.MERCEDES)
        .uuid(UUID.randomUUID())
        .build();
  }
}
