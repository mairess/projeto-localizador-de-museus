package com.betrybe.museumfinder.exception;

/**
 * The type Museum not found exception.
 */
public class MuseumNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Museum not found exception.
   */
  public MuseumNotFoundException() {
    super("Museu não encontrado!");
  }

}