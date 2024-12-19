package com.nextrad.vietphucstore.controllers;

import com.nextrad.vietphucstore.entities.product.ProductType;
import com.nextrad.vietphucstore.services.imps.ProductTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-types")
public class ProductTypeController {

  private ProductTypeService productTypeService;

  public ProductTypeController(ProductTypeService productTypeService) {
    this.productTypeService = productTypeService;
  }

  @PostMapping
  public ResponseEntity<ProductType> createProductType(@RequestBody ProductType productType) {
    return ResponseEntity.ok(productTypeService.createProductType(productType));
  }

  @GetMapping
  public ResponseEntity<List<ProductType>> getAllProductTypes() {
    return ResponseEntity.ok(productTypeService.getAllProductTypes());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductType> getProductTypeById(@PathVariable UUID id) {
    Optional<ProductType> productType = productTypeService.getProductTypeById(id);
    return productType.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductType> updateProductType(@PathVariable UUID id,
      @RequestBody ProductType productTypeDetails) {
    return ResponseEntity.ok(productTypeService.updateProductType(id, productTypeDetails));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProductType(@PathVariable UUID id) {
    productTypeService.deleteProductType(id);
    return ResponseEntity.noContent().build();
  }
}
