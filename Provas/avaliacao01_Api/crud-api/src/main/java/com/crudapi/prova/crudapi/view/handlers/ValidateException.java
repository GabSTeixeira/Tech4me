package com.crudapi.prova.crudapi.view.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidateException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        final List<FieldError> erros = ex.getBindingResult().getFieldErrors();

        final List<String> listaErros = new ArrayList<>();

        for (FieldError fe : erros) {
            // nome do atributo que deu erro mais a mensagem do erro
            listaErros.add(fe.getField() + " " + fe.getDefaultMessage());
        }

        return new ResponseEntity<Object>(listaErros, HttpStatus.NOT_ACCEPTABLE);
    }

    // método para tratar uma List<MusicRequest>
    @ExceptionHandler({ ConstraintViolationException.class })
    protected ResponseEntity<List<Map<String,List<String>>>> handleConstraintViolation(final ConstraintViolationException ex,
            final WebRequest request) {
        
        final List<Map<String,List<String>>> errors = new ArrayList<>();
        
        
        final Object[] objectErrorArray = ex.getConstraintViolations().toArray();
        final ArrayList<String> objectErrorArrayList = new ArrayList<>();
        
        for(int i = 0; i < objectErrorArray.length; i++) {
            
            // pega o indice da requisição pra saber em qual request especifico deu erro
            objectErrorArrayList.add((((ConstraintViolation<?>)objectErrorArray[i]).getPropertyPath().toString() + "|" +
            
            // pega o atributo desse request que deu erro
            ((ConstraintViolation<?>)objectErrorArray[i]).getPropertyPath().toString().substring(32) + " " +
            
            //pega a mensagem de erro do atributo
            ((ConstraintViolation<?>)objectErrorArray[i]).getMessage()));

            //resultado = postManyMusicDocument.musics[indice para separa de qual request é].nome do atributo que deu erro :| mensagem do erro
        }
        
        // para o countRequest funcionar é necessario deixar a lista em ordem crescente
        Collections.sort(objectErrorArrayList); 
        
        ObjectError objectError = new ObjectError();
        char objectErrorIndex = objectErrorArrayList.get(0).charAt(29); // indice do primeiro erro
        char objectErrorIndexActual = '_';
        int countRequests = 1;
        int index = 0; 
        for (String e : objectErrorArrayList) {
  
            //e.charAt(29) = postManyMusicDocument.musics[--->ESTE É O INDICE 29<---]
            objectErrorIndexActual = e.charAt(29);

            if(index == (objectErrorArrayList.size() -1)) {
                
                // quando esta no ultimo elemento e ele é do mesmo request que o anterior 
                if(e.charAt(29) == objectErrorIndex) {
                    objectError.setRequestNumber(countRequests);
                    objectError.setAErrorInTheList(e.substring(e.indexOf("|")+1));
                    objectError.setErrorMessageResult();
                 
                    errors.add(objectError.getErrorMessageResult());
                }

                // quando esta no ultimo elemento e ele é de um request diferente do anterior
                if(e.charAt(29) != objectErrorIndex) {
                    // fechamos o request anterior e adicionamos na lista 
                    objectError.setRequestNumber(countRequests);
                    objectError.setErrorMessageResult();
                    errors.add(objectError.getErrorMessageResult());

                    // adicionamos o request atual que é o ultimo na lista e paramos o loop
                    objectError = new ObjectError();
                    objectError.setRequestNumber(countRequests+1);
                    objectError.setAErrorInTheList(e.substring(e.indexOf("|")+1));
                    objectError.setErrorMessageResult();
                    errors.add(objectError.getErrorMessageResult());
                }
                break;
            }
            
            // se a request atual for diferente a ultima ele adiona na lista e cria um novo map
            if (e.charAt(29) != objectErrorIndex) {                
                objectError.setRequestNumber(countRequests);
                objectError.setErrorMessageResult();
                errors.add(objectError.getErrorMessageResult());
                
                objectErrorIndex = objectErrorIndexActual;
                objectError = new ObjectError();
                countRequests++;
            }
            
            objectError.setAErrorInTheList(e.substring(e.indexOf("|")+1));
            index++;
        }
        return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
    }
}
