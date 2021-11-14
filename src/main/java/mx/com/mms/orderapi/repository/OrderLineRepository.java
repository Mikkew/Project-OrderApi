package mx.com.mms.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import mx.com.mms.orderapi.entity.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, String>, JpaSpecificationExecutor<OrderLine> {

}
