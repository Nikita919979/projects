package com.epam.rd.edu.petproject.controller;

import com.epam.rd.edu.petproject.dto.TransitDto;
import com.epam.rd.edu.petproject.service.TransitService;
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
@RequestMapping("/transits")
public class TransitController {

  private TransitService transitDtoService;

  @Timed
  @GetMapping("/{uuid}")
  public TransitDto getTransitDto(@PathVariable String uuid) {
    return transitDtoService.read(UUID.fromString(uuid));
  }

  @Timed
  @GetMapping
  public List<TransitDto> getTransitsList() {
    return transitDtoService.getAll();
  }

  @Timed
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TransitDto createTransitDto(@RequestBody TransitDto transitDto) {
    return transitDtoService.create(transitDto);
  }

  @Timed
  @PutMapping
  public void updateTransitDto(@RequestBody TransitDto transitDto) {
    transitDtoService.update(transitDto);
  }

  @Timed
  @DeleteMapping("/{uuid}")
  public void deleteTransitDto(@PathVariable String uuid) {
    transitDtoService.delete(UUID.fromString(uuid));
  }
}
