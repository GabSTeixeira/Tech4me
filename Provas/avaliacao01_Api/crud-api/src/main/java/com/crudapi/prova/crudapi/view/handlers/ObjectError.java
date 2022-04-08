package com.crudapi.prova.crudapi.view.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectError {
    
    private int requestNumber;
    private ArrayList<String> listErrors = new ArrayList<>();
    private Map<String,List<String>> errorMessageResult;
    
    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }
    public void setAErrorInTheList (String listError) {
        
        listErrors.add(listError);
        //System.out.println("\n\n\n"+restult+"\n\n\n");
    }
    public Map<String,List<String>> getErrorMessageResult() {
        return errorMessageResult;
    }
    
    public void setErrorMessageResult() {

        this.errorMessageResult = new HashMap<String,List<String>>();
        
        this.errorMessageResult.put(new String("Requisição com erro número: "+this.requestNumber),
        this.listErrors);
    }
    
}
