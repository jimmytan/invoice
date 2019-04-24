package io.zola.controller.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDTO {

  @NotNull
  private String invoiceNumber;

  @NotNull
  private String poNumber;

  @NotNull
  private long amountInCents;
  private String date;
  private String timeStamp;
}
