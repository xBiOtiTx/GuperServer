/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erstudio.guper.dto;

/**
 *
 * @author Евгений
 */
public class ErrorDto {
    private final String type = "error";
    
    private String error_type;
    private String error_description;

    public ErrorDto(String error_type, String error_description) {
        this.error_type = error_type;
        this.error_description = error_description;
    }

    public String getError_type() {
        return error_type;
    }

    public void setError_type(String error_type) {
        this.error_type = error_type;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}
