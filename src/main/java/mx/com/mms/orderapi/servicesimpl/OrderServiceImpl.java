package mx.com.mms.orderapi.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.com.mms.orderapi.converters.OrderConverter;
import mx.com.mms.orderapi.entity.Order;
import mx.com.mms.orderapi.exceptions.GeneralException;
import mx.com.mms.orderapi.exceptions.ResourceNotFoundException;
import mx.com.mms.orderapi.repository.OrderRepository;
import mx.com.mms.orderapi.services.IOrderService;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	private OrderConverter converter = new OrderConverter();

	@Override
	public Page<Order> getAllOrders(Specification<Order> spec, Pageable pageable) {
		Page<Order> pagedResult = orderRepository.findAll(spec, pageable);
		try {
			if(pagedResult.hasContent()) {
				return pagedResult;
			}
			throw new ResourceNotFoundException("Orders not found");
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public Order getById(String id) {
		try {
			return orderRepository
					.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Order not found"));
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order update(Order order) {
		try {
			Order orderUpdate = orderRepository
					.findById(order.getOrderId())
					.orElseThrow(() -> new ResourceNotFoundException("Order not found"));
			orderUpdate = converter.toEntity(order);
			return orderUpdate;
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public Order updateById(String id, Order order) {
		try {
			Order orderUpdate = orderRepository
					.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Order not found"));
			orderUpdate = converter.toEntity(order);
			return orderUpdate;
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public Order delete(String id) {
		try {
			Order orderDelete = orderRepository
					.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Order not found"));
			orderRepository.delete(orderDelete);
			return orderDelete;
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}
	
}
