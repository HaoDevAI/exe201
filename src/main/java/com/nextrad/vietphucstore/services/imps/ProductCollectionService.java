package com.nextrad.vietphucstore.services.imps;

import com.nextrad.vietphucstore.entities.product.ProductCollection;
import com.nextrad.vietphucstore.repositories.product.ProductCollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductCollectionService {

    private ProductCollectionRepository productCollectionRepository;

    public ProductCollectionService(ProductCollectionRepository productCollectionRepository) {
        this.productCollectionRepository = productCollectionRepository;
    }

    public ProductCollection createProductCollection(ProductCollection collection) {
        return productCollectionRepository.save(collection);
    }

    public List<ProductCollection> getAllProductCollections() {
        return productCollectionRepository.findAll();
    }

    public Optional<ProductCollection> getProductCollectionById(UUID id) {
        return productCollectionRepository.findById(id);
    }

    public ProductCollection updateProductCollection(UUID id, ProductCollection collectionDetails) {
        return productCollectionRepository.findById(id)
                .map(collection -> {
                    collection.setName(collectionDetails.getName());
                    collection.setDeleted(collectionDetails.isDeleted());
                    return productCollectionRepository.save(collection);
                })
                .orElseThrow(() -> new RuntimeException("ProductCollection not found with id " + id));
    }

    public void deleteProductCollection(UUID id) {
        productCollectionRepository.deleteById(id);
    }
}
