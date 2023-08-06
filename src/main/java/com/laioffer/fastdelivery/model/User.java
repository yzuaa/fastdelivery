package com.laioffer.fastdelivery.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "users")
@JsonDeserialize(builder = User.Builder.class)
public class User implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   private String username;
   @JsonIgnore
   private String password;
   @JsonIgnore
   private boolean enabled;

   public User() {
   }

   private User(Builder builder) {
      this.username = builder.username;
      this.password = builder.password;
      this.enabled = builder.enabled;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isEnabled() {
      return enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public static class Builder {
      @JsonProperty("username")
      private String username;
      @JsonProperty("password")
      private String password;
      @JsonProperty("enabled")
      private boolean enabled;

      public Builder setUsername(String username) {
         this.username = username;
         return this;
      }

      public Builder setPassword(String password) {
         this.password = password;
         return this;
      }

      public Builder setEnabled(boolean enabled) {
         this.enabled = enabled;
         return this;
      }

      public User build() {
         return new User(this);
      }
   }
}

