package com.shaheen.fileservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class PermissionGroup extends AbstractEntity {

  private String groupName;
  @OneToMany(mappedBy = "permissionGroup")
  private Set<Item> items;
  @OneToMany(mappedBy = "permissionGroup")
  private Set<PermissionUser> permissionUsers;
}
