package com.shaheen.fileservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PermissionUser extends AbstractEntity{
  @Column(name = "user_email")
  private String email;
  private Integer permissionLevel;
  @ManyToOne
  private PermissionGroup permissionGroup;
}
