package com.epam.rd.edu.petproject.controller;

import com.epam.rd.edu.petproject.dto.CarDto;
import com.epam.rd.edu.petproject.service.CarService;
import com.epam.rd.edu.petproject.utils.aspect.Timed;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

  private CarService carDtoService;

  @Timed
  @GetMapping("/{uuid}")
  public CarDto getCarDto(@PathVariable String uuid) {
    return carDtoService.read(UUID.fromString(uuid));
  }

  @Timed
  @GetMapping
  public List<CarDto> getCarsList() {
    return carDtoService.getAll();
  }

  @Timed
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CarDto createCarDto(@RequestBody CarDto carDto) {
    return carDtoService.create(carDto);
  }

  @Timed
  @PutMapping
  public void updateCarDto(@RequestBody CarDto carDto) {
    carDtoService.update(carDto);
  }

  @Timed
  @DeleteMapping(value = "/{uuid}")
  public void deleteCarDto(@PathVariable String uuid) {
    carDtoService.delete(UUID.fromString(uuid));
  }
}
