package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable {
  private final BigDecimal weight;
  private final LocalDate expirationDate;

  public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
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
    return "Food Product | name: " + name() + ", category: " + category() + ", price: " + price() + ", expirationDate: " + expirationDate + ", weight: " + weight;
  }

  @Override
  public BigDecimal calculateShippingCost() {
    return null;
  }

  @Override
  public double weight() {
    return 0;
  }

  @Override
  public LocalDate expirationDate() {
    return null;
  }

  @Override
  public boolean isExpired() {
    return Perishable.super.isExpired();
  }
}
