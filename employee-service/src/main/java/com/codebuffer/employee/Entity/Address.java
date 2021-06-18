package com.codebuffer.employee.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

@Entity
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long addressId;

  @NonNull private String street;
  @NonNull private String city;
  @NonNull private String state;

  public Address(
      Long addressId, @NonNull String street, @NonNull String city, @NonNull String state) {
    this.addressId = addressId;
    this.street = street;
    this.city = city;
    this.state = state;
  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  @NonNull
  public String getStreet() {
    return street;
  }

  public void setStreet(@NonNull String street) {
    this.street = street;
  }

  @NonNull
  public String getCity() {
    return city;
  }

  public void setCity(@NonNull String city) {
    this.city = city;
  }

  @NonNull
  public String getState() {
    return state;
  }

  public void setState(@NonNull String state) {
    this.state = state;
  }
}
