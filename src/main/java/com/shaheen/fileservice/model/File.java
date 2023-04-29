package com.shaheen.fileservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class File extends AbstractEntity{
  @Lob
  private String content;
  @OneToOne
  private Item item;
}
