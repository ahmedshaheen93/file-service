package com.shaheen.fileservice.model;

public enum Type {
  SPACE("Space") , FOLDER("Folder") , FILE("File");
  private final String value;

  Type(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
