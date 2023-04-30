package com.shaheen.fileservice.errorhandling;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ClientException {
  public BadRequestException(String message) {
    super(message);
  }

  @Override
  protected HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }
}
