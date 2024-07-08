package com.example.shopbook.services;

import com.example.shopbook.dtos.ProductDTO;
import com.example.shopbook.dtos.ProductImageDTO;
import com.example.shopbook.exceptions.DataNotFoundException;
import com.example.shopbook.exceptions.InvalidParamException;
import com.example.shopbook.models.Category;
import com.example.shopbook.models.Product;
import com.example.shopbook.models.ProductImage;
import com.example.shopbook.repositories.CategoryRepository;
import com.example.shopbook.repositories.ProductImageRepository;
import com.example.shopbook.repositories.ProductRepository;
import com.example.shopbook.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.shopbook.models.ProductImage.MAXIMUM_IMAGES_PER_PRODUCT;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;
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
                .description(productDTO.getDescription())
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
    public Page<ProductResponse> getAllProduct(PageRequest pageRequest) {
        //Lấy danh sách sản phẩm theo trang(page) và giới hạn limit
        return productRepository
                .findAll(pageRequest).map(ProductResponse::fromProduct);
    }

    @Override
    public Product updateProduct(long id, ProductDTO productDTO) throws Exception {
        Product existingProduct = getProductById(id);
        if (existingProduct != null){
            //copy các thuộc tính từ DTO -> Product
            //Có thể sư dụng ModelMapper
            Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() ->
                            new DataNotFoundException(
                                    "Không tìm thấy category với id: " + productDTO.getCategoryId()));
            existingProduct.setName(productDTO.getName());
            existingProduct.setCategory(existingCategory);
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setThumbnail(productDTO.getThumbnail());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(productRepository::delete);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws Exception{
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Không tìm thấy product với id: " + productImageDTO.getProductId()));
        ProductImage newProductImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        //Không cho insert quá 5 ảnh cho 1 sản phẩm
        int size = productImageRepository.findByProductId(productId).size();
        if (size >= MAXIMUM_IMAGES_PER_PRODUCT){
            throw new InvalidParamException("Số lượng ảnh không quá " + MAXIMUM_IMAGES_PER_PRODUCT);
        }
        return productImageRepository.save(newProductImage);
    }
}
