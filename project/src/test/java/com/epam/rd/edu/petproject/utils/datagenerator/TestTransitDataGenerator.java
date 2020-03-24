package com.epam.rd.edu.petproject.utils.datagenerator;

import com.epam.rd.edu.petproject.converter.CarConverter;
import com.epam.rd.edu.petproject.converter.CityConverter;
import com.epam.rd.edu.petproject.converter.TransitConverter;
import com.epam.rd.edu.petproject.converter.UserConverter;
import com.epam.rd.edu.petproject.dto.CarDto;
import com.epam.rd.edu.petproject.dto.CityDto;
import com.epam.rd.edu.petproject.dto.TransitDto;
import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.model.Car.CarModel;
import com.epam.rd.edu.petproject.model.Transit;
import com.epam.rd.edu.petproject.model.Transit.Status;
import com.epam.rd.edu.petproject.model.User.Role;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestTransitDataGenerator {

  private final CarConverter carConverter = new CarConverter();
  private final CityConverter cityConverter = new CityConverter();
  private final UserConverter userConverter = new UserConverter();
  private final TransitConverter transitConverter = new TransitConverter(carConverter,
      userConverter, cityConverter);

  public List<TransitDto> generateRequestTransitDtoList(int count) {
    return IntStream.range(0, count)
        .mapToObj(TestTransitDataGenerator::generateTransitDto)
        .collect(Collectors.toList());
  }

  public TransitDto generateTransitDto(int counter) {
    return TransitDto.builder()
        .id(counter)
        .city_from(TestCityDataGenerator.generateCityDto(counter))
        .city_to(TestCityDataGenerator.generateCityDto(counter))
        .user(TestUserDataGenerator.generateUserDtoWithRandomRole(counter))
        .car(TestCarDataGenerator.generateCarDtoWithRandomModel(counter))
        .driver(TestUserDataGenerator.generateUserDtoWithRandomRole(counter))
        .status(Arrays.stream(Status.values()).findAny().get())
        .build();
  }

  public Transit getTransit(TransitDto transitDto) {
    return transitConverter.toEntity(transitDto);
  }

  public List<Transit> getTransitList(List<TransitDto> transitDtoList) {
    return transitConverter.toEntityList(transitDtoList);
  }

  public TransitDto generateRealEntity(int counter) {
    UserDto userDto = UserDto.builder()
        .email("nikita919979@gmail.com")
        .familyName("Poddubskiy")
        .id(1)
        .login("admin")
        .role(Role.ADMIN)
        .name("Nikita")
        .build();

    UserDto userDtoDriver = UserDto.builder()
        .email("qwerty919979@gmail.com")
        .familyName("Petrov")
        .id(2)
        .login("dispatcher")
        .role(Role.DISPATCHER)
        .name("Mike")
        .build();

    CityDto city1 = CityDto.builder()
        .name("Dnepr")
        .id(1)
        .build();

    CityDto city2 = CityDto.builder()
        .name("Rome")
        .id(2)
        .build();

    CarDto carDto = CarDto.builder()
        .carNumber("016-01OA")
        .carTechnicalPassport("vp4Tz6G6")
        .fully_Functional(false)
        .releaseDate(LocalDate.of(2017, 02, 18))
        .id(1)
        .model(CarModel.VAN)
        .build();

    return TransitDto.builder()
        .user(userDto)
        .city_to(city1)
        .city_from(city2)
        .status(Status.INPROGRESS)
        .driver(userDtoDriver)
        .car(carDto)
        .id(counter)
        .build();
  }
}
