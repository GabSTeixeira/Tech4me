package com.crudapi.prova.crudapi.view.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    protected ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
            final WebRequest request) {
        
        final List<String> errors = new ArrayList<>();
        
        char objectErrorIndex = '_';
        int countRequests = 0;
        
        final Object[] objectErrorArray = ex.getConstraintViolations().toArray();
        final ArrayList<String> objectErrorArrayList = new ArrayList<>();
        
        for(int i = 0; i < objectErrorArray.length; i++) {
            
            // pega o indice da requisição pra saber em qual request especifica deu erro
            objectErrorArrayList.add((((ConstraintViolation<?>)objectErrorArray[i]).getPropertyPath().toString() + "|" +
            
            // pega o atributo desse do objeto que deu erro
            ((ConstraintViolation<?>)objectErrorArray[i]).getPropertyPath().toString().substring(32) + " " +
            
            //pega a mensagem de erro do atributo
            ((ConstraintViolation<?>)objectErrorArray[i]).getMessage()));
        }
        
        // para o countRequest funcionar é necessario deixar a lista em ordem crescente
        Collections.sort(objectErrorArrayList); 
        
        String messageError;
        String messageErrorResult = "";
        int index = 0;
        for (String e : objectErrorArrayList) {
            
            // verifica se o objt do erro atual é igual ao objt do erro anterior, ser for diferente cria
            // uma nova Request, assim separa de qual objt é cada erro

            //e.charAt(29) = postManyMusicDocument.musics[--->ESTE É O INDICE 29<---]

            if (index == 0 || !(e.charAt(29) == objectErrorIndex)) {
                objectErrorIndex = e.charAt(29);
                countRequests++;
                errors.add(messageErrorResult);

            }            
            
            objectErrorIndex = e.charAt(29);

            messageError = String.format("Requisição com problema número %s: ", countRequests);
            messageErrorResult = String.format(messageError + "\n%s", e.substring(e.indexOf("|")+1, e.length()));

            
            index++;
        }

        
        return new ResponseEntity(errors, HttpStatus.NOT_ACCEPTABLE);
    }
}
