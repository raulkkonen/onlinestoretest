package academy.digitallab.store.shopping.client;

import academy.digitallab.store.shopping.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "ms-product" , url = "http://ms-product-backend-svc:8092")
public interface ProductClient {

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);
}
