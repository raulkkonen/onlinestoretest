package academy.digitallab.store.shopping.repository;

import academy.digitallab.store.shopping.domain.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}
