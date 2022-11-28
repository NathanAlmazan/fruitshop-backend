package com.ntea.fruitshop.products;

import com.ntea.fruitshop.global.EntityCrudServices;
import com.ntea.fruitshop.global.errors.EntityNotFoundException;
import com.ntea.fruitshop.global.errors.InvalidRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServices implements EntityCrudServices<Product, ProductDto, String> {
    private final ProductRepo productRepo;
    private final ProductFactory productFactory;
    private final UnitPricesRepo unitPricesRepo;
    private final UnitPricesFactory unitPricesFactory;

    public ProductServices(ProductRepo productRepo, ProductFactory productFactory, UnitPricesRepo unitPricesRepo, UnitPricesFactory unitPricesFactory) {
        this.productRepo = productRepo;
        this.productFactory = productFactory;
        this.unitPricesRepo = unitPricesRepo;
        this.unitPricesFactory = unitPricesFactory;
    }

    @Override
    public ProductDto create(ProductDto data) {
        if (entityExists(data.getProductCode()))
            throw new InvalidRequestException("Product with code of " + data.getProductCode() + " already exists.");

        productRepo.save(productFactory.requestToEntity(data));
        data.getUnitPrices().forEach(unit -> {
            unit.setProductCode(data.getProductCode());
            unitPricesRepo.save(unitPricesFactory.requestToEntity(unit));
        });

        return productFactory.entityToResponse(getById(data.getProductCode()), null);
    }

    @Override
    public ProductDto update(ProductDto data) {
        Product updatedProduct = productRepo.findById(data.getProductCode())
                .map(prod -> productRepo.save(productFactory.requestToUpdatedEntity(data, prod)))
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Product with code of " + data.getProductCode() + " is not found.");
                });

        data.getUnitPrices().forEach(price -> {
            UnitPrices current = updatedProduct.getUnitPrices().stream()
                    .filter(unit -> unit.getUnitPriceKey().getUnitCode().equals(price.getUnitType()))
                    .findAny()
                    .orElse(null);

            if (current != null) {
                unitPricesRepo.findById(current.getUnitPriceKey())
                        .map(unit -> {
                            unit.setUnitPrice(price.getUnitPrice());
                            return unitPricesRepo.save(unit);
                        })
                        .orElseThrow(() -> {
                            throw new EntityNotFoundException("Unit price not found.");
                        });
            } else {
                price.setProductCode(updatedProduct.getProductCode());
                unitPricesRepo.save(unitPricesFactory.requestToEntity(price));
            }
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

    public List<String> getAllProductNames() {
        List<String> productNames = new ArrayList<>();
        List<Product> products = productRepo.findAllActiveProducts();

        products.forEach(product -> productNames.add(product.getProductName()));
        return productNames;
    }
}
