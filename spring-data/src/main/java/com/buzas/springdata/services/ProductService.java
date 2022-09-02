package com.buzas.springdata.services;

import com.buzas.springdata.products.ProductDto;
import com.buzas.springdata.products.ProductDtoMapper;
import com.buzas.springdata.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final ProductDtoMapper mapper;

    public Page<ProductDto> findAllByFilters(Double minimumFilter, Double maximumFilter, int page, int size) {
        return productRepo.findAllByFilters(
                maximumFilter, minimumFilter, PageRequest.of(page, size)).map(mapper::map);
    }

    public Optional<ProductDto> findById(Long id) {
        return productRepo.findById(id)
                .map(mapper::map);
    }

    public void save(ProductDto productDto) {
        productRepo.save(mapper.map(productDto));
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }

    public void deleteAll() {
        productRepo.deleteAll();
    }
}
