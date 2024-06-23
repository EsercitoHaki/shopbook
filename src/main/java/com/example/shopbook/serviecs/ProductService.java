package com.example.shopbook.serviecs;

import com.example.shopbook.dtos.ProductDTO;
import com.example.shopbook.exceptions.DataNotFoundException;
import com.example.shopbook.models.Category;
import com.example.shopbook.models.Product;
import com.example.shopbook.repositories.CategoryRepository;
import com.example.shopbook.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
        Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Không tìm thấy category với id: " + productDTO.getCategoryId()));
        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .category(existingCategory)
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(long productId) throws Exception{
        return productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Không tìm thấy product with id = " + productId
                ));
    }

    @Override
    public Page<Product> getAllProduct(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Product updateProduct(long id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProduct(long id) {

    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}
