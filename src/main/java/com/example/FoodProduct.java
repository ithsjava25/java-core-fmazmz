package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable {
  private final BigDecimal weight;
  private final LocalDate expirationDate;

  protected FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
    if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Price must be greater than zero");
    }
    super(id, name, category, price);

    if (weight == null || weight.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Weight cannot be negative.");
    }
    this.weight = weight;
    this.expirationDate = expirationDate;
  }

  @Override
  String productDetails() {
    return "Food: " + name() + ", Expires: " + expirationDate;
  }

  @Override
  public BigDecimal calculateShippingCost() {
    return weight.multiply(BigDecimal.valueOf(50));
  }

  @Override
  public double weight() {
    return weight.doubleValue();
  }

  @Override
  public LocalDate expirationDate() {
    return expirationDate;
  }

  @Override
  public boolean isExpired() {
    return Perishable.super.isExpired();
  }
}
