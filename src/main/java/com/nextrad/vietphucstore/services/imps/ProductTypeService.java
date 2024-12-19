package com.nextrad.vietphucstore.services.imps;

import com.nextrad.vietphucstore.entities.product.ProductType;
import com.nextrad.vietphucstore.repositories.product.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductTypeService {

  private ProductTypeRepository productTypeRepository;

  public ProductTypeService(ProductTypeRepository productTypeRepository) {
    this.productTypeRepository = productTypeRepository;
  }

  public ProductType createProductType(ProductType productType) {
    return productTypeRepository.save(productType); // Lưu productType vào database
  }

  public List<ProductType> getAllProductTypes() {
    return productTypeRepository.findAll(); // Lấy tất cả productType từ database
  }

  public Optional<ProductType> getProductTypeById(UUID id) {
    return productTypeRepository.findById(id); // Tìm productType theo ID
  }

  public ProductType updateProductType(UUID id, ProductType productTypeDetails) {
    return productTypeRepository.findById(id)
        .map(productType -> {
          productType.setName(productTypeDetails.getName());
          productType.setDeleted(productTypeDetails.isDeleted());
          return productTypeRepository.save(productType); // Cập nhật productType
        })
        .orElseThrow(() -> new RuntimeException("ProductType not found with id " + id));
  }

  public void deleteProductType(UUID id) {
    productTypeRepository.deleteById(id); // Xóa productType theo ID
  }
}
