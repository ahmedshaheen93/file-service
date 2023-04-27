package com.shaheen.fileservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Item extends AbstractEntity{

  @Enumerated(EnumType.STRING)
  private Type type;

  private String name;
  @ManyToOne
  private PermissionGroup permissionGroup;
  @OneToMany(mappedBy = "item")
  private Set<File> files;
}
