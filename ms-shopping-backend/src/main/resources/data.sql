INSERT INTO tbl_invoices (id, number_invoice, description, customer_id, state,  created_date,created_user,row_version) VALUES(1, '0001', 'invoice office items', 1,'CREATED',CURRENT_TIMESTAMP,'admin',0);


INSERT INTO tbl_invoce_items ( invoice_id, product_id, quantity, price ) VALUES(1, 1 , 1, 178.89);
INSERT INTO tbl_invoce_items ( invoice_id, product_id, quantity, price)  VALUES(1, 2 , 2, 12.5);
INSERT INTO tbl_invoce_items ( invoice_id, product_id, quantity, price)  VALUES(1, 3 , 1, 40.06);
