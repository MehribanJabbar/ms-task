package az.ingress.ms.mapper;

import az.ingress.ms.dao.entity.ProductEntity;
import az.ingress.ms.model.request.SaveProductRequest;
import az.ingress.ms.model.request.UpdateProductRequest;
import az.ingress.ms.model.response.ProductResponse;

public class ProductMapper {

    public static ProductResponse buildToResponse(ProductEntity product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    public static ProductEntity buildToEntity(SaveProductRequest productRequest){
        return ProductEntity.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .build();
    }

    public static void updateToProduct(ProductEntity product, UpdateProductRequest productRequest){

    }
}
