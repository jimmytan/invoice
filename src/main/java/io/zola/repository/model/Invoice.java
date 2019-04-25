package io.zola.repository.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  private String invoiceNumber;
  private String poNumber;
  private long amountInCents;
  private LocalDate dueDate;
  private LocalDateTime createAt;
}
