package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Warehouse {
  private static final Map<String, Warehouse> INSTANCES = new ConcurrentHashMap<>();

  private final String name;
  private final List<Product> products = new ArrayList<>();
  private final Set<UUID> changedProducts = new HashSet<>();


  private Warehouse(String name) {
    this.name = name;
  }

  public static Warehouse getInstance(String name) {
    return INSTANCES.computeIfAbsent(name, Warehouse::new);
  }

  public String getName() {
    return name;
  }

  public List<Product> getProducts() {
    return Collections.unmodifiableList(products);
  }

  public Optional<Product> getProductById(UUID id) {
    return products.stream()
            .filter(product -> product.uuid().equals(id))
            .findFirst();
  }

  public void addProduct(Product product) throws IllegalArgumentException {
    if (product == null) {
      throw new IllegalArgumentException("Product cannot be null.");
    }
    products.add(product);
  }

  public void remove(UUID id){
    products.removeIf(product -> product.uuid().equals(id));
  }

  public List<Perishable> expiredProducts(){
    return products.stream()
            .filter(product -> product instanceof Perishable)
            .map(product -> (Perishable) product)
            .filter(Perishable::isExpired)
            .toList();
  }

  public List<Shippable> shippableProducts(){
    return products.stream()
            .filter(product -> product instanceof Shippable)
            .map(product -> (Shippable) product)
            .toList();
  }

  public void updateProductPrice(UUID id, BigDecimal price){
    Product product = getProductById(id)
            .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    product.price(price);
    changedProducts.add(id);
  }

  public void clearProducts() {
    products.clear();
    changedProducts.clear();
  }

  public List<Product> getChangedProducts() {
    return products.stream()
            .filter(product -> changedProducts.contains(product.uuid()))
            .toList();
  }

  public boolean isEmpty() {
    return products.isEmpty();
  }

  public Map<Category, List<Product>> getProductsGroupedByCategories() {
    return products.stream()
            .collect(Collectors.groupingBy(Product::category));
  }
}
