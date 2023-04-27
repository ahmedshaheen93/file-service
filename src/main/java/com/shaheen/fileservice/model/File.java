package com.shaheen.fileservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class File extends AbstractEntity{
  private Byte[] content;
  @ManyToOne
  private Item item;
}
