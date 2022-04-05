package com.crudapi.prova.crudapi.view.controller;

import java.util.List;

import javax.validation.Valid;

import com.crudapi.prova.crudapi.service.MusicService;
import com.crudapi.prova.crudapi.shared.MusicDto;
import com.crudapi.prova.crudapi.view.model.MusicRequest;
import com.crudapi.prova.crudapi.view.model.MusicResponse;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/musicas")
public class MusicController {
    
    ModelMapper ApiMapper = new ModelMapper();

    private final MusicService service;

    MusicController (MusicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MusicResponse> postMusic(@RequestBody @Valid MusicRequest music) {
        
        MusicDto documentMusicDto = ApiMapper.map(music, MusicDto.class);

        MusicDto documentMusicDtoResponse = service.createMusicDocument(documentMusicDto).get();
        
        MusicResponse documentMusicResponse = ApiMapper.map(documentMusicDtoResponse, MusicResponse.class);
        return new ResponseEntity<>(documentMusicResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicResponse> getUniqueMusic (@PathVariable String id) {
        MusicResponse documentMusicFound = service.getMusicDocument(id).isPresent() ?
        ApiMapper.map(service.getMusicDocument(id).get(), MusicResponse.class) : null;

        if(documentMusicFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(documentMusicFound, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<MusicDto>> getTodos () {
        return new ResponseEntity<>(service.getTodos(), HttpStatus.OK);
    }
}
