package com.example.domain;

import java.math.BigDecimal;

public interface Shippable {
  BigDecimal calculateShippingCost();
  double weight();
}
