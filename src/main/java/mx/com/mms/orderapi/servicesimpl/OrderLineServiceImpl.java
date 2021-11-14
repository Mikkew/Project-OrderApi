package mx.com.mms.orderapi.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.com.mms.orderapi.converters.OrderLineConverter;
import mx.com.mms.orderapi.entity.OrderLine;
import mx.com.mms.orderapi.exceptions.GeneralException;
import mx.com.mms.orderapi.exceptions.ResourceNotFoundException;
import mx.com.mms.orderapi.repository.OrderLineRepository;
import mx.com.mms.orderapi.services.IOrderLineService;

@Slf4j
@Service
@Transactional
public class OrderLineServiceImpl implements IOrderLineService {
	
	@Autowired
	private OrderLineRepository lineRepository;
	
	private OrderLineConverter converter = new OrderLineConverter();
	
	@Override
	public Page<OrderLine> getAllLines(Specification<OrderLine> spec, Pageable pageable) {
		Page<OrderLine> pagedResult = lineRepository.findAll(spec, pageable);
		try {
			if(pagedResult.hasContent()) {
				return pagedResult;
			}
			throw new ResourceNotFoundException("Lines not found");
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public OrderLine getById(String id) {
		try {
			return lineRepository
					.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Line not found"));
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public OrderLine save(OrderLine line) {
		return lineRepository.save(line);
	}

	@Override
	public OrderLine update(OrderLine line) {
		try {
			OrderLine lineUpdate = lineRepository
					.findById(line.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Lines not found"));
			lineUpdate = converter.toEntity(line);
			return lineUpdate;
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public OrderLine updateById(String id, OrderLine line) {
		try {
			OrderLine lineUpdate = lineRepository
					.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Lines not found"));
			lineUpdate = converter.toEntity(line);
			return lineUpdate;
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public OrderLine delete(String id) {
		try {
			OrderLine line = lineRepository
					.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Line not found"));
			lineRepository.delete(line);
			return line;
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

}
