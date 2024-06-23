package com.example.shopbook.serviecs;

import com.example.shopbook.dtos.ProductDTO;
import com.example.shopbook.exceptions.DataNotFoundException;
import com.example.shopbook.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface IProductService {
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException;
    Product getProductById(long id) throws Exception;
    Page<Product> getAllProduct(PageRequest pageRequest);
    Product updateProduct(long id, ProductDTO productDTO);
    void deleteProduct(long id);
    boolean existsByName(String name);
}
