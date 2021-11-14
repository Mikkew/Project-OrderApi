package mx.com.mms.orderapi.entity;

import java.time.LocalDateTime;

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
@Table(name = "ORDER_LINES")
public class OrderLine {
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(100)")
	private String id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(
			name = "order_id", 
			referencedColumnName = "order_id",
			nullable = false,
			foreignKey = @ForeignKey(name = "FK_ORDERLINE_ORDER"))
	private Order order;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(
			name = "product_id", 
			referencedColumnName = "product_id",
			nullable = false,
			foreignKey = @ForeignKey(name = "FK_ORDERLINE_PRODUCT"))
	private Product product;
	 
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "quantity", nullable = false)
	private Double quantity;
		
	@Column(name = "total", nullable = false)
	private Double total;
	
	@CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
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
