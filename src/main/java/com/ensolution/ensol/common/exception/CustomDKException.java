package com.ensolution.ensol.common.exception;

import org.springframework.dao.DuplicateKeyException;

public class CustomDKException extends DuplicateKeyException {
  /*
  entityName ==> company, stack ... is Data Table Name
  dataType ==> ID, Name ... is Field Name
  duplicateValue => is Field Value
  */
  public CustomDKException(String entityName, String dataType, String duplicateValue, Throwable cause) {
    super(String.format("Failed to add %s. Duplicate key for %s: %s", entityName, dataType, duplicateValue), cause);
  }
}
