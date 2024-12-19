package com.nextrad.vietphucstore.controllers;

import com.nextrad.vietphucstore.entities.product.ProductSize;
import com.nextrad.vietphucstore.services.imps.ProductSizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-sizes")
public class ProductSizeController {

  private ProductSizeService productSizeService;

  public ProductSizeController(ProductSizeService productSizeService) {
    this.productSizeService = productSizeService;
  }

  @PostMapping
  public ResponseEntity<ProductSize> createProductSize(@RequestBody ProductSize productSize) {
    return ResponseEntity.ok(productSizeService.createProductSize(productSize));
  }

  @GetMapping
  public ResponseEntity<List<ProductSize>> getAllProductSizes() {
    return ResponseEntity.ok(productSizeService.getAllProductSizes());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductSize> getProductSizeById(@PathVariable UUID id) {
    Optional<ProductSize> productSize = productSizeService.getProductSizeById(id);
    return productSize.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductSize> updateProductSize(@PathVariable UUID id,
      @RequestBody ProductSize productSizeDetails) {
    return ResponseEntity.ok(productSizeService.updateProductSize(id, productSizeDetails));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProductSize(@PathVariable UUID id) {
    productSizeService.deleteProductSize(id);
    return ResponseEntity.noContent().build();
  }
}
