package com.shaheen.fileservice.errorhandling;

public abstract class ClientException extends BaseException {
  protected ClientException(String message) {
    super(message);
  }
}
