/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.roosevelt.seniorproject.nflpickem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mruth
 */
public class ValidationErrorMessage {
    private String errorDetail = "Input Field Validation Error";
    
    private List<FieldErrorMessage> errors = new ArrayList();

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public List<FieldErrorMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorMessage> errors) {
        this.errors = errors;
    }
    
    public void addFieldErrorMessage(FieldErrorMessage fem) {
        errors.add(fem);
    }
   
    
}
