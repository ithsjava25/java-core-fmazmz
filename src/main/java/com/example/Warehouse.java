package com.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class Warehouse {
  private String name;
  private List<Product> products;


  public Warehouse getInstance(String name) {return null;}
  public List<Product> getProducts() {
    return null;
  }
  public Optional<Product> getProductById(UUID id) {
    return Optional.empty();

  }
  public void addProduct(Product product) throws IllegalArgumentException {
    if (product == null) {
      throw new IllegalArgumentException("Product cannot be null.");
    }
    // add product
  }

  public void remove(UUID id){}
  public List<Perishable> expiredProducts(){return null;}
  public List<Shippable> shippableProducts(){return null;}

  public void updateProductPrice(UUID id, BigDecimal price) throws NoSuchElementException {
    if (getProductById(id).isEmpty()) {
      throw new NoSuchElementException("Product not found with ID: " + id);
    }
  }

}
