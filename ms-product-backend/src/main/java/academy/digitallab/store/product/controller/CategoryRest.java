package academy.digitallab.store.product.controller;

import academy.digitallab.store.product.domain.Category;
import academy.digitallab.store.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryRest {

    private final ProductService productService ;

    // -------------------Retrieve All Category--------------------------------------------
    @GetMapping
    public ResponseEntity<List<Category>> listPCategory(){
        List<Category> categories ;

        categories = productService.findCategoryAll();
            if (categories.isEmpty()){
                return ResponseEntity.noContent().build();
            }

        return ResponseEntity.ok(categories);
    }

    // -------------------Retrieve Single Category------------------------------------------
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) {
        log.info("Fetching Category with id {}", id);
        Category category =  productService.getCategory(id);

        return ResponseEntity.ok(category);
    }

    // -------------------Create a Category-------------------------------------------
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
        log.info("Creating Category: {}",category);

        Category categoryCreate =  productService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryCreate);
    }
}
