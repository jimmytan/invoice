package io.zola.repository.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Builder
@Entity(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;
  private String invoiceNumber;
  private String poNumber;
  private long amountCents;
  private LocalDate dueDate;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
