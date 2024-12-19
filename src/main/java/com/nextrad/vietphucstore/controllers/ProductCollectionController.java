package com.nextrad.vietphucstore.controllers;

import com.nextrad.vietphucstore.entities.product.ProductCollection;
import com.nextrad.vietphucstore.services.imps.ProductCollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-collections")
public class ProductCollectionController {

  private ProductCollectionService productCollectionService;

  public ProductCollectionController(ProductCollectionService productCollectionService) {
    this.productCollectionService = productCollectionService;
  }

  @PostMapping
  public ResponseEntity<ProductCollection> createProductCollection(@RequestBody ProductCollection collection) {
    return ResponseEntity.ok(productCollectionService.createProductCollection(collection));
  }

  @GetMapping
  public ResponseEntity<List<ProductCollection>> getAllProductCollections() {
    return ResponseEntity.ok(productCollectionService.getAllProductCollections());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductCollection> getProductCollectionById(@PathVariable UUID id) {
    Optional<ProductCollection> collection = productCollectionService.getProductCollectionById(id);
    return collection.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductCollection> updateProductCollection(@PathVariable UUID id,
      @RequestBody ProductCollection collectionDetails) {
    return ResponseEntity.ok(productCollectionService.updateProductCollection(id, collectionDetails));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProductCollection(@PathVariable UUID id) {
    productCollectionService.deleteProductCollection(id);
    return ResponseEntity.noContent().build();
  }
}
