package com.nathanael.fruitshop.products;

import com.nathanael.fruitshop.global.EntityCrudServices;
import com.nathanael.fruitshop.global.errors.EntityNotFoundException;
import com.nathanael.fruitshop.global.errors.InvalidRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices implements EntityCrudServices<Product, ProductDto, String> {
    private final ProductRepo productRepo;
    private final ProductFactory productFactory;

    public ProductServices(ProductRepo productRepo, ProductFactory productFactory) {
        this.productRepo = productRepo;
        this.productFactory = productFactory;
    }

    @Override
    public ProductDto create(ProductDto data) {
        if (entityExists(data.getProductCode()))
            throw new InvalidRequestException("Product with code of " + data.getProductCode() + " already exists.");

        return productFactory.entityToResponse(productRepo.save(productFactory.requestToEntity(data)), null);
    }

    @Override
    public ProductDto update(ProductDto data) {
        Product updatedProduct = productRepo.findById(data.getProductCode())
                .map(prod -> productRepo.save(productFactory.requestToUpdatedEntity(data, prod)))
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Product with code of " + data.getProductCode() + " is not found.");
                });

        return productFactory.entityToResponse(updatedProduct, null);
    }

    @Override
    public ProductDto delete(String id) {
        ProductDto deletedProduct = getDtoById(id, null);

        if (!deletedProduct.getIsActive()) productRepo.deleteById(id);
        else productRepo.setProductInactive(id);

        return deletedProduct;
    }

    @Override
    public ProductDto getDtoById(String id, List<String> additionalFields) {
        return productFactory.entityToResponse(getById(id), additionalFields);
    }

    @Override
    public Product getById(String id) {
        return productRepo.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Product with code of " + id + " is not found.");
        });
    }

    @Override
    public boolean entityExists(String id) {
        return productRepo.findById(id).isPresent();
    }

    @Override
    public List<ProductDto> getAll(List<String> additionalFields) {
        if (additionalFields.contains("inactive")) return productFactory.entityListToResponse(productRepo.findAllInActiveProducts(), additionalFields);
        return productFactory.entityListToResponse(productRepo.findAllActiveProducts(), additionalFields);
    }
}
