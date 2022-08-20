package academy.digitallab.store.product.service;

import academy.digitallab.store.product.domain.Category;
import academy.digitallab.store.product.domain.Product;
import academy.digitallab.store.product.error.exception.BusinessException;
import academy.digitallab.store.product.error.exception.ResourceNotFoundException;
import academy.digitallab.store.product.repository.CategoryRepository;
import academy.digitallab.store.product.repository.ProductRepository;
import academy.digitallab.store.product.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductService{


    private final ProductRepository productRepository;


    private final CategoryRepository categoryRepository;


    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + id));
    }

    @Override
    public Product createProduct(Product product) {
        Product productDB = productRepository.findByNumberProduct( product.getNumberProduct () );

        if (null!=productDB) {
            return productDB;
        }
        product.setStatus (Constant.STATE_CREATED );
        product.setCreatedBy ( "admin" );

        return productRepository.save ( product );


    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());
        productDB.setNumberProduct(product.getNumberProduct());
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());
        productDB.setUpdatedBy ( "admin" );
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);

        productDB.setStatus(Constant.STATE_DELETED);
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {

        return productRepository.findByCategory(category);
    }

    @Transactional
    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = getProduct(id);

        Double stock =  productDB.getStock() + quantity;
        if (stock< 0) {
            throw new BusinessException("El stock minimo es "+productDB.getStock());
        }
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }

    //---CATEGORY--------------------------------------------
    @Override
    public Category createCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {

        Category categoryDB = getCategory(category.getId());

        categoryDB.setName(category.getName());
        return categoryRepository.save(categoryDB);
    }

    @Override
    public Category getCategory(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + id));
    }

    @Override
    public void deleteCategory(Long id) {

        categoryRepository.deleteById(id);

    }

    @Override
    public List <Category> findCategoryAll() {

        return categoryRepository.findAll();
    }
}
