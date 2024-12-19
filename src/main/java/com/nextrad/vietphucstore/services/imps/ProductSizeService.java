package com.nextrad.vietphucstore.services.imps;

import com.nextrad.vietphucstore.entities.product.ProductSize;
import com.nextrad.vietphucstore.repositories.product.ProductSizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductSizeService {

  private ProductSizeRepository productSizeRepository;

  public ProductSizeService(ProductSizeRepository productSizeRepository) {
    this.productSizeRepository = productSizeRepository;
  }

  public ProductSize createProductSize(ProductSize productSize) {
    return productSizeRepository.save(productSize); // Lưu productSize vào database
  }

  public List<ProductSize> getAllProductSizes() {
    return productSizeRepository.findAll(); // Lấy tất cả productSize từ database
  }

  public Optional<ProductSize> getProductSizeById(UUID id) {
    return productSizeRepository.findById(id); // Tìm productSize theo ID
  }

  public ProductSize updateProductSize(UUID id, ProductSize productSizeDetails) {
    return productSizeRepository.findById(id)
        .map(productSize -> {
          productSize.setName(productSizeDetails.getName());
          productSize.setDeleted(productSizeDetails.isDeleted());
          return productSizeRepository.save(productSize); // Cập nhật productSize
        })
        .orElseThrow(() -> new RuntimeException("ProductSize not found with id " + id));
  }

  public void deleteProductSize(UUID id) {
    productSizeRepository.deleteById(id); // Xóa productSize theo ID
  }
}
