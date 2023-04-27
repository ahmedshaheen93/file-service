package com.shaheen.fileservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class UserPermission extends AbstractEntity {
  @Column(name = "user_email")
  private String email;
  private Integer permissionLevel;
  @ManyToMany
  @JoinTable(
      name = "user_groups_permissions",
      joinColumns = @JoinColumn(name = "user_permission_id"),
      inverseJoinColumns = @JoinColumn(name = "group_permission_id"))
  private Set<GroupPermission> groupPermissions;
  @OneToMany(mappedBy = "userPermission")
  private Set<Item> items;
}
