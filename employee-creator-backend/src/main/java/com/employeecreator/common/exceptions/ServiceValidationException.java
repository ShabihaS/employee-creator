package com.employeecreator.common.exceptions;

import java.util.ArrayList;
import java.util.Map;

import com.employeecreator.common.ValidationErrors;

public class ServiceValidationException extends RuntimeException {

  private ValidationErrors errors;

  public ServiceValidationException(ValidationErrors errors) {
    super("Validation Failed");
    this.errors = errors;
  }

  public Map<String, ArrayList<String>> getErrors() {
    return this.errors.getErrors();
  }
}
