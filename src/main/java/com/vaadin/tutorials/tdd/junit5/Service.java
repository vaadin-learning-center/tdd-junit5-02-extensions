package com.vaadin.tutorials.tdd.junit5;

import java.io.Serializable;

public class Service implements Serializable {
  public int add(int a, int b){
    return a + b;
  }
}
