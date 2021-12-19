INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 1', 100, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 2', 200, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 3', 300, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 4', 400, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 5', 500, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 6', 600, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 7', 700, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 8', 800, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 9', 900, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 10', 1000, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 11', 1100, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 12', 1200, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 13', 1300, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 14', 1400, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 15', 1500, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 16', 1600, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 17', 1700, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 18', 1800, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 19', 1900, SYSDATE(), SYSDATE());
INSERT INTO PRODUCTS(product_id,name,price,created_at,updated_at) VALUES(UUID(),'producto 20', 2000, SYSDATE(), SYSDATE());

INSERT INTO ORDERS(order_id,total,created_at,updated_at) VALUES (UUID(),1000,SYSDATE(), SYSDATE());
INSERT INTO ORDERS(order_id,total,created_at,updated_at) VALUES (UUID(),2000,SYSDATE(), SYSDATE());
INSERT INTO ORDERS(order_id,total,created_at,updated_at) VALUES (UUID(),3000,SYSDATE(), SYSDATE());
INSERT INTO ORDERS(order_id,total,created_at,updated_at) VALUES (UUID(),4000,SYSDATE(), SYSDATE());
INSERT INTO ORDERS(order_id,total,created_at,updated_at) VALUES (UUID(),5000,SYSDATE(), SYSDATE());
INSERT INTO ORDERS(order_id,total,created_at,updated_at) VALUES (UUID(),6000,SYSDATE(), SYSDATE());
INSERT INTO ORDERS(order_id,total,created_at,updated_at) VALUES (UUID(),7000,SYSDATE(), SYSDATE());
INSERT INTO ORDERS(order_id,total,created_at,updated_at) VALUES (UUID(),8000,SYSDATE(), SYSDATE());
INSERT INTO ORDERS(order_id,total,created_at,updated_at) VALUES (UUID(),9000,SYSDATE(), SYSDATE());
INSERT INTO ORDERS(order_id,total,created_at,updated_at) VALUES (UUID(),10000,SYSDATE(), SYSDATE());

INSERT INTO ORDER_LINES(id,order_id, product_id, price,quantity,total,created_at,updated_at) VALUES (UUID(),(SELECT order_id FROM ORDERS ORDER BY RAND() LIMIT 1),(SELECT product_id FROM PRODUCTS ORDER BY RAND() LIMIT 1),10,100,1000,SYSDATE(), SYSDATE());
INSERT INTO ORDER_LINES(id,order_id, product_id, price,quantity,total,created_at,updated_at) VALUES (UUID(),(SELECT order_id FROM ORDERS ORDER BY RAND() LIMIT 1),(SELECT product_id FROM PRODUCTS ORDER BY RAND() LIMIT 1),20,200,2000,SYSDATE(), SYSDATE());
INSERT INTO ORDER_LINES(id,order_id, product_id, price,quantity,total,created_at,updated_at) VALUES (UUID(),(SELECT order_id FROM ORDERS ORDER BY RAND() LIMIT 1),(SELECT product_id FROM PRODUCTS ORDER BY RAND() LIMIT 1),30,300,3000,SYSDATE(), SYSDATE());
INSERT INTO ORDER_LINES(id,order_id, product_id, price,quantity,total,created_at,updated_at) VALUES (UUID(),(SELECT order_id FROM ORDERS ORDER BY RAND() LIMIT 1),(SELECT product_id FROM PRODUCTS ORDER BY RAND() LIMIT 1),40,400,4000,SYSDATE(), SYSDATE());
INSERT INTO ORDER_LINES(id,order_id, product_id, price,quantity,total,created_at,updated_at) VALUES (UUID(),(SELECT order_id FROM ORDERS ORDER BY RAND() LIMIT 1),(SELECT product_id FROM PRODUCTS ORDER BY RAND() LIMIT 1),50,500,5000,SYSDATE(), SYSDATE());          
INSERT INTO ORDER_LINES(id,order_id, product_id, price,quantity,total,created_at,updated_at) VALUES (UUID(),(SELECT order_id FROM ORDERS ORDER BY RAND() LIMIT 1),(SELECT product_id FROM PRODUCTS ORDER BY RAND() LIMIT 1),60,600,6000,SYSDATE(), SYSDATE());
INSERT INTO ORDER_LINES(id,order_id, product_id, price,quantity,total,created_at,updated_at) VALUES (UUID(),(SELECT order_id FROM ORDERS ORDER BY RAND() LIMIT 1),(SELECT product_id FROM PRODUCTS ORDER BY RAND() LIMIT 1),70,700,7000,SYSDATE(), SYSDATE());
INSERT INTO ORDER_LINES(id,order_id, product_id, price,quantity,total,created_at,updated_at) VALUES (UUID(),(SELECT order_id FROM ORDERS ORDER BY RAND() LIMIT 1),(SELECT product_id FROM PRODUCTS ORDER BY RAND() LIMIT 1),80,800,8000,SYSDATE(), SYSDATE());
INSERT INTO ORDER_LINES(id,order_id, product_id, price,quantity,total,created_at,updated_at) VALUES (UUID(),(SELECT order_id FROM ORDERS ORDER BY RAND() LIMIT 1),(SELECT product_id FROM PRODUCTS ORDER BY RAND() LIMIT 1),90,900,9000,SYSDATE(), SYSDATE());
INSERT INTO ORDER_LINES(id,order_id, product_id, price,quantity,total,created_at,updated_at) VALUES (UUID(),(SELECT order_id FROM ORDERS ORDER BY RAND() LIMIT 1),(SELECT product_id FROM PRODUCTS ORDER BY RAND() LIMIT 1),100,1000,10000,SYSDATE(), SYSDATE());

INSERT INTO ROLES(role_id,name,created_at,updated_at) VALUES(UUID(),"ADMIN",SYSDATE(), SYSDATE());
INSERT INTO ROLES(role_id,name,created_at,updated_at) VALUES(UUID(),"USER",SYSDATE(), SYSDATE());

INSERT INTO USERS(user_id,first_name,last_name,username,email,password,created_at,updated_at) VALUES(UUID(),"Juan","Molina","Mikkelios","mikkew@gmail.com","$2a$10$nXsXJCZ0S1nHCPMotiM83eHpwOh3R5ckb7/eQwLKdSq413OzSsVFC",SYSDATE(), SYSDATE());

INSERT INTO ROLE_USERS(user_id, role_id) VALUES((SELECT user_id FROM USERS ORDER BY RAND() LIMIT 1),(SELECT role_id FROM ROLES ORDER BY RAND() LIMIT 1));
