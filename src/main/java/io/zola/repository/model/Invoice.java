package io.zola.repository.model;


import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

@Data
public class Invoice {

  private String invoiceNumber;
  private String poNumber;
  private long amountInCents;
  private Date date;
  private Timestamp timeStamp;
}
