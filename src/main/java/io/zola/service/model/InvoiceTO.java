package io.zola.service.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceTO {

  private String invoiceNumber;
  private String poNumber;
  private long amountInCents;
  private LocalDate date;
  private Timestamp timeStamp;

}
