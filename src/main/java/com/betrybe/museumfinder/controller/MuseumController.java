package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Museum controller.
 */
@RestController
@RequestMapping(value = "/museums")
public class MuseumController {

  private final MuseumServiceInterface service;

  /**
   * Instantiates a new Museum controller.
   *
   * @param service the service
   */
  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  /**
   * Gets museum.
   *
   * @param id the id
   * @return the museum
   */
  @GetMapping("/{id}")
  public ResponseEntity<MuseumDto> getMuseum(@PathVariable Long id) {

    Museum museum = service.getMuseum(id);

    return ResponseEntity.status(HttpStatus.OK).body(ModelDtoConverter.modelToDto(museum));
  }

  /**
   * Create museum museum.
   *
   * @param newMuseum the new museum
   * @return the museum
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MuseumDto createMuseum(@RequestBody MuseumCreationDto newMuseum) {

    Museum createdMuseum = service.createMuseum(ModelDtoConverter.dtoToModel(newMuseum));

    return ModelDtoConverter.modelToDto(createdMuseum);
  }

  /**
   * Gets closest museum.
   *
   * @param lat       the lat
   * @param lng       the lng
   * @param maxDistKm the max dist km
   * @return the closest museum
   */
  @GetMapping("/closest")
  public MuseumDto getClosestMuseum(
      @RequestParam double lat,
      @RequestParam double lng,
      @RequestParam("max_dist_km") double maxDistKm
  ) {
    Coordinate coordinate = new Coordinate(lat, lng);

    Museum closestMuseum = service.getClosestMuseum(coordinate, maxDistKm);

    return ModelDtoConverter.modelToDto(closestMuseum);

  }

}