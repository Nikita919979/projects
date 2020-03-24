package com.epam.rd.edu.petproject.controller;

import com.epam.rd.edu.petproject.dto.CityDto;
import com.epam.rd.edu.petproject.service.CityService;
import com.epam.rd.edu.petproject.utils.aspect.Timed;
import java.util.List;
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
@RequestMapping("/cities")
public class CityController {

  private CityService cityDtoService;

  @Timed
  @GetMapping("/{id}")
  public CityDto getCityDto(@PathVariable String id) {
    return cityDtoService.read(Integer.parseInt(id));
  }

  @Timed
  @GetMapping
  public List<CityDto> getCitiesList() {
    return cityDtoService.getAll();
  }

  @Timed
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CityDto createCityDto(@RequestBody CityDto cityDto) {
    return cityDtoService.create(cityDto);
  }

  @Timed
  @PutMapping
  public void updateCityDto(@RequestBody CityDto cityDto) {
    cityDtoService.update(cityDto);
  }

  @Timed
  @DeleteMapping(value = "/{id}")
  public void deleteCityDto(@PathVariable String id) {
    cityDtoService.delete(Integer.parseInt(id));
  }
}
