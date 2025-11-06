package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {
  private final int warrantyMonths;
  private final BigDecimal weight;

  protected ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
    super(id, name, category, price);

    if (warrantyMonths < 0) {
      throw new IllegalArgumentException("Warranty months cannot be negative.");
    }
    this.warrantyMonths = warrantyMonths;
    this.weight = weight;
  }

  @Override
  String productDetails() {
    return "Electronics: " + name() + ", Warranty: " + warrantyMonths;
  }

  @Override
  public BigDecimal calculateShippingCost() {
    BigDecimal basePrice = BigDecimal.valueOf(79);
    BigDecimal additionalPrice = BigDecimal.valueOf(49);

    if (weight.compareTo(BigDecimal.valueOf(5.0)) > 0) {
      return basePrice.add(additionalPrice);
    };
    return basePrice;
  }

  @Override
  public double weight() {
    return weight.doubleValue();
  }
}
