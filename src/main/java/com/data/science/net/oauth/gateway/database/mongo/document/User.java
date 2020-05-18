package com.data.science.net.oauth.gateway.database.mongo.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document
public class User implements UserDetails {

  @Id private ObjectId id;

  @Indexed(unique = true, direction = IndexDirection.DESCENDING)
  private String username;

  private String password;
  private boolean enable;
  private List<Rule> rules = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.rules;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.enable;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.enable;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.enable;
  }

  @Override
  public boolean isEnabled() {
    return this.enable;
  }
}
