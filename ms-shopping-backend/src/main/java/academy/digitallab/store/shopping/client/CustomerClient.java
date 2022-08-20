package academy.digitallab.store.shopping.client;

import academy.digitallab.store.shopping.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "ms-customer" , url = "http://ms-customer-backend-svc:8092")

public interface CustomerClient {
    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) ;
}
