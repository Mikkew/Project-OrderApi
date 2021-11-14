package mx.com.mms.orderapi.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "product_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(100)")
	private String productId;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,2)")
	private Double price;

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

	@OneToMany(mappedBy = "product")
	private List<OrderLine> lines;
}
