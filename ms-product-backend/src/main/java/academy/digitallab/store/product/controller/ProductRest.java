package academy.digitallab.store.product.controller;

import academy.digitallab.store.product.domain.Category;
import academy.digitallab.store.product.domain.Product;
import academy.digitallab.store.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping (value = "/products")
@RequiredArgsConstructor
public class ProductRest {


    private final ProductService productService ;

    // -------------------Retrieve All Products--------------------------------------------
    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> products ;
        if (null ==  categoryId){
             products = productService.listAllProduct();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }

    // -------------------Retrieve Single Product------------------------------------------
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        log.info("Fetching Product with id {}", id);
        Product product =  productService.getProduct(id);

        return ResponseEntity.ok(product);
    }

    // -------------------Create a Product-------------------------------------------
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        log.info("Creating Product: {}",product);

        Product productCreate =  productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    //------------------Update Product--------------------------------
   @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
       log.info("Updating Product with id {}", id);
        product.setId(id);
        Product productDB =  productService.updateProduct(product);

        return ResponseEntity.ok(productDB);
    }

    // ------------------- Delete a Product-----------------------------------------
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product productDelete = productService.deleteProduct(id);

        return ResponseEntity.ok(productDelete);
    }

    // -------------------Update Stock to Product-------------------------------------------
    @PutMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable  Long id ,@RequestParam(name = "quantity", required = true) Double quantity){
        log.info("update stock Product : {} with quantity {} ", id, quantity);
        Product product = productService.updateStock(id, quantity);

        return ResponseEntity.ok(product);
    }

}
