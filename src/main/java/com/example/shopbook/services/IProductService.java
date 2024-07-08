package com.example.shopbook.services;

import com.example.shopbook.dtos.ProductDTO;
import com.example.shopbook.dtos.ProductImageDTO;
import com.example.shopbook.exceptions.DataNotFoundException;
import com.example.shopbook.models.Product;
import com.example.shopbook.models.ProductImage;
import com.example.shopbook.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws DataNotFoundException;
    Product getProductById(long id) throws Exception;
    Page<ProductResponse> getAllProduct(PageRequest pageRequest);
    Product updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
    ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws Exception;
}
