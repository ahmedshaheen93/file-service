package com.shaheen.fileservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class GroupPermission extends AbstractEntity {

  private String groupName;
  @OneToMany(mappedBy = "groupPermission")
  private Set<Item> items;
  @ManyToMany(mappedBy = "groupPermissions")
  private Set<UserPermission> userPermissions;
}
