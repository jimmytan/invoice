package io.zola.service.model;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

@Data
public class InvoiceTO {

  private String invoiceNumber;
  private String poNumber;
  private long amountInCents;
  private Date date;
  private Timestamp timeStamp;

}
