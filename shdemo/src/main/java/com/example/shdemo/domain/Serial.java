package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "serial.all", query = "Select ser from Serial ser")
})
public class Serial {
  private Long id;
  private String serial = "";

  public Serial() {
  }

  public Serial(String serial) {
    super();
    this.serial = serial;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getSerial() {
    return serial;
  }
  public void setSerial(String serial) {
    this.serial = serial;
  }
}