package mx.com.mms.orderapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import mx.com.mms.orderapi.entity.Order;

public interface IOrderService {
	public Page<Order> getAllOrders(Specification<Order> spec, Pageable pageable);
	
	public Order getById(String id);
	
	public Order save(Order order);

	public Order update(Order order);
	
	public Order updateById(String id, Order order);
	
	public Order delete(String id);	
}
