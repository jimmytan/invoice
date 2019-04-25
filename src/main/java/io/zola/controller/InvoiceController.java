package io.zola.controller;

import io.zola.Converter;
import io.zola.CrudService;
import io.zola.Validator;
import io.zola.controller.model.InvoiceDTO;
import io.zola.exception.InvoiceException;
import io.zola.service.context.InvoiceSearchContext;
import io.zola.service.model.InvoiceTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

  @Autowired
  private Converter<InvoiceDTO, InvoiceTO>  converter;

  @Autowired
  private CrudService<InvoiceTO, InvoiceSearchContext> invoiceService;

  @Autowired
  private Validator<InvoiceDTO> validator;

  @RequestMapping(value = Routes.INVOICE_CREATE_PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<InvoiceDTO> create(@RequestBody @Valid @NotNull InvoiceDTO invoiceDTO) {
    validator.validate(invoiceDTO);
    return Optional.of(invoiceDTO).map(converter::to)
        .map(invoiceService::create)
        .map(converter::from).map(data -> new ResponseEntity(data, HttpStatus.CREATED))
        .orElseThrow(() -> new InvoiceException("unable to create invoice " + invoiceDTO.toString()));
  }

  @RequestMapping(value = Routes.INVOICE_SEARCH_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<InvoiceDTO>> search(@RequestParam(required = false) String invoiceNumber, @RequestParam(required = false) String poNumber, @RequestParam(required = false, defaultValue = "20") Integer pageSize, @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    InvoiceSearchContext invoiceSearchContext = InvoiceSearchContext.builder().invoiceNumber(invoiceNumber).poNumber(poNumber).pageable(pageable).build();
    List<InvoiceDTO> invoices = invoiceService.search(invoiceSearchContext).stream().map(converter::from).collect(Collectors.toList());
    return ResponseEntity.ok(invoices);
  }

}
