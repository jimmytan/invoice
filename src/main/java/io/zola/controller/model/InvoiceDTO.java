package io.zola.controller.model;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
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
