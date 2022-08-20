package academy.digitallab.store.product.repository;

import academy.digitallab.store.product.domain.Category;
import academy.digitallab.store.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

    public List<Product> findByCategory(Category category);
    public Product findByNumberProduct(String numberProduct);
}
