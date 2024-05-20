package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type General controller advice.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  /**
   * Handle invalid coordinate response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(InvalidCoordinateException.class)
  public ResponseEntity<String> handleInvalidCoordinateException(RuntimeException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }

  /**
   * Handle museum not found exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(MuseumNotFoundException.class)
  public ResponseEntity<String> handleMuseumNotFoundException(RuntimeException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }

  /**
   * Handle other exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleOtherException(RuntimeException exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }

}