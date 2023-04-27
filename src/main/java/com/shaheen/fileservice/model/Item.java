package com.shaheen.fileservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item extends AbstractEntity {

  @Enumerated(EnumType.STRING)
  private Type type;

  private String name;

  @OneToOne
  private Item parent;

  @ManyToOne
  private GroupPermission groupPermission;
  @ManyToOne
  private UserPermission userPermission;
  @OneToOne
  private File file;
}
