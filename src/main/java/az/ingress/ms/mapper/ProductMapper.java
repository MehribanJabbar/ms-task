package az.ingress.ms.mapper;

import az.ingress.ms.dao.entity.ProductEntity;
import az.ingress.ms.model.request.SaveProductRequest;
import az.ingress.ms.model.request.UpdateProductRequest;
import az.ingress.ms.model.response.ProductResponse;

public class ProductMapper {

    public static ProductResponse buildToResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .build();
    }

    public static ProductEntity buildToEntity(SaveProductRequest request) {
        return ProductEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
    }

    public static void updateToProduct(ProductEntity productEntity, UpdateProductRequest request) {
        productEntity.setDescription(request.getDescription());
        productEntity.setName(request.getName());
        productEntity.setPrice(request.getPrice());
        productEntity.setStock(request.getStock());
    }
}
