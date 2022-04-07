package com.crudapi.prova.crudapi.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.crudapi.prova.crudapi.service.MusicService;
import com.crudapi.prova.crudapi.shared.MusicDto;
import com.crudapi.prova.crudapi.view.model.MusicRequest;
import com.crudapi.prova.crudapi.view.model.MusicResponse;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/musicas")
public class MusicController {
    
    ModelMapper ApiMapper = new ModelMapper();

    private final MusicService service;

    MusicController (MusicService service) {
        this.service = service;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<MusicResponse> postUniqueMusicDocument (@RequestBody @Valid MusicRequest music) {
        
        MusicDto documentMusicDto = ApiMapper.map(music, MusicDto.class);

        MusicDto documentMusicDtoResponse = service.createMusicDocument(documentMusicDto).get();
        
        MusicResponse documentMusicResponse = ApiMapper.map(documentMusicDtoResponse, MusicResponse.class);
        return new ResponseEntity<>(documentMusicResponse, HttpStatus.CREATED);
    }

    @PostMapping("/adicionar/varios")
    public ResponseEntity<List<MusicResponse>> postManyMusicDocument (@RequestBody @Valid List<MusicRequest> musics) {

        List<MusicDto> documentListMusicDto = musics.stream()
        .map(m -> ApiMapper.map(m, MusicDto.class)).collect(Collectors.toList());

        List<MusicResponse> documentListMusicResponse = service.createManyMusicDocument(documentListMusicDto).get()
        .stream().map(m -> ApiMapper.map(m, MusicResponse.class)).collect(Collectors.toList());

        return new ResponseEntity<> (documentListMusicResponse, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<MusicResponse> getUniqueMusicDocument (@PathVariable String id) {
        
        // só converte se tiver algum valor dentro do Optional, se for empty a variavel recebe null
        MusicResponse documentMusicFound = service.getMusicDocument(id).isPresent() ?
        ApiMapper.map(service.getMusicDocument(id).get(), MusicResponse.class) : null;

        if(documentMusicFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(documentMusicFound, HttpStatus.FOUND);
    }

    @GetMapping("/buscar/todas")
    public ResponseEntity<List<MusicResponse>> getAllMusicDocument () {
        
        // só converte se tiver algum valor dentro do Optional, se for empty a variavel recebe null
        List<MusicResponse> documentAllMusicFound = service.getAllMusicDocument().isPresent() ?
        service.getAllMusicDocument().get().stream().map(m -> ApiMapper.map(m, MusicResponse.class))
        .collect(Collectors.toList()) : null;
        
        if(documentAllMusicFound == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(documentAllMusicFound, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<MusicResponse> deleteUniqueMusicDocument (@PathVariable String id) {

        Optional<MusicDto> documentMusicDeleted = service.deleteMusicDocument(id);

        if(documentMusicDeleted.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        MusicResponse documentMusicDeletedResponse = ApiMapper.map(documentMusicDeleted.get(), MusicResponse.class);

        return new ResponseEntity<>(documentMusicDeletedResponse, HttpStatus.OK);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<MusicResponse> putUniqueMusicDocument (@PathVariable String id, @Valid @RequestBody MusicRequest music) {

        MusicDto documentMusicDto = ApiMapper.map(music, MusicDto.class);
        MusicDto documentMusicDtoResponse = service.updateMusicDocument(id, documentMusicDto).get();
        MusicResponse documentMusicResponse = ApiMapper.map(documentMusicDtoResponse, MusicResponse.class);

        return new ResponseEntity<>(documentMusicResponse, HttpStatus.OK);
    }
}
