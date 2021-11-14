package mx.com.mms.orderapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import mx.com.mms.orderapi.entity.OrderLine;

public interface IOrderLineService {	
	public Page<OrderLine> getAllLines(Specification<OrderLine> spec, Pageable pageable);
	
	public OrderLine getById(String id);
	
	public OrderLine save(OrderLine line);
	
	public OrderLine update(OrderLine line);

	public OrderLine updateById(String id, OrderLine line);
	
	public OrderLine delete(String id);
}
