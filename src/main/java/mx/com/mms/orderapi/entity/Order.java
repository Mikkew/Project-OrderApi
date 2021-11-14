package mx.com.mms.orderapi.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ORDERS")
public class Order {
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(100)")
	private String orderId;
	
	@Column(name = "total", nullable = false, columnDefinition = "DECIMAL(10,2)")
	private Double total;
	
	@CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<OrderLine> lines;
	
	@PrePersist
    protected void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
	
	@PreUpdate
    protected void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

}
