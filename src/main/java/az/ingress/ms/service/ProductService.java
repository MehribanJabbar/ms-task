package az.ingress.ms.service;

import az.ingress.ms.dao.entity.ProductEntity;
import az.ingress.ms.dao.repository.ProductRepository;
import az.ingress.ms.exception.NotFoundException;
import az.ingress.ms.mapper.ProductMapper;
import az.ingress.ms.model.request.SaveProductRequest;
import az.ingress.ms.model.request.UpdateProductRequest;
import az.ingress.ms.model.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.ms.mapper.ProductMapper.*;
import static az.ingress.ms.model.enums.Status.DELETED;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProducts(){
        List<ProductResponse> products= productRepository.findAll()
                .stream().map(ProductMapper::buildToResponse)
                .collect(Collectors.toList());
        return products;
    }

    public ProductResponse getProductById(Long id){
        var product = fetchProductIfExist(id);
        return buildToResponse(product);
    }

    public void createProduct(SaveProductRequest request){
        productRepository.save(buildToEntity(request));
    }


    public void updateProduct(Long id, UpdateProductRequest productRequest){
        var product=fetchProductIfExist(id);
        updateToProduct(product, productRequest);
        productRepository.save(product);
    }

    public void deleteProduct(Long id){
        var product=fetchProductIfExist(id);
        product.setStatus(DELETED);
        productRepository.save(product);
    }

    public ProductEntity fetchProductIfExist(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("PRODUCT_NOT_FOUND"));
    }
}
