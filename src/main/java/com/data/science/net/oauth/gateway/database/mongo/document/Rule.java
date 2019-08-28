package com.data.science.net.oauth.gateway.database.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rule implements GrantedAuthority {

  @Id private String id;
  private String name;

  @Override
  public String getAuthority() {
    return this.id;
  }
}
