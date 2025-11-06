package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Category {
  public final String name;
  private static final Map<String, Category> CACHE = new ConcurrentHashMap<>();

  private Category(String name){
    this.name = name;
  }

  public static Category of(String name){
    if (name == null){
      throw new IllegalArgumentException("Category name can't be null");
    }

    String trimmed = name.trim();
    if (trimmed.isEmpty()){
      throw new IllegalArgumentException("Category name can't be blank");
    }

    // normalize
    String normalized = trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();

    return CACHE.computeIfAbsent(normalized, Category::new);
  }

  // public accessors
  public String getName(){
    return name;
  }

}
