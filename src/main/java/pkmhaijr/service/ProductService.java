package pkmhaijr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.FilterType;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.repository.ProductRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by patry on 23/04/17.
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        log.info("Adding product: " + product);
        return productRepository.save(product);
    }

    public Product findProductById(Integer id) {
        log.info("Searching for product with id: " + id);
        return productRepository.findOne(id);
    }

    public List<Product> findAllProducts() {
        log.info("Getting all products");
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Product product) {
        log.info("Deleting product: " + product);
        productRepository.delete(product);
    }

    public Product updateProduct(Product newProduct) {
        log.info("Updating product: " + newProduct);
        Product oldProduct = findProductById(newProduct.getId());
        if (oldProduct != null) {
            return productRepository.save(newProduct);
        } else {
            throw new IllegalArgumentException("There is no produvt with id " + newProduct.getId() + " in the database");
        }
    }

    public void deleteAllProducts() {
        log.info("Deleting all products");
        productRepository.deleteAll();
    }

    public Integer countProducts() {
        log.info("Returning number of products");
        return Math.toIntExact(productRepository.count());
    }

    public ArrayList<Product> getSortedProduct(String prefix){
        return productRepository.findProducts(prefix);
    }

    public ArrayList<Product> getSortedProduct(String searchPhrase, Genre genreType) {
        return productRepository.findProducts(searchPhrase, genreType);
    }
}
