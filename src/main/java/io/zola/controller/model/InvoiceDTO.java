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

  private long id;

  @NotNull
  private String invoiceNumber;

  @NotNull
  private String poNumber;

  @NotNull
  private Long amountCents;

  @NotNull
  private String dueDate;
  private String createdAt;
}
