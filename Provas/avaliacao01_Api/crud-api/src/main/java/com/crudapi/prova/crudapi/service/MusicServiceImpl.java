package com.crudapi.prova.crudapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crudapi.prova.crudapi.model.Music;
import com.crudapi.prova.crudapi.repository.MusicRepository;
import com.crudapi.prova.crudapi.shared.MusicDto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceImpl implements MusicService {


    private final ModelMapper API_MAPPER = new ModelMapper();
    private final MusicRepository repository;
    
    MusicServiceImpl (MusicRepository music) {
        this.repository = music;
    }


    @Override
    public Optional<MusicDto> createMusicDocument(MusicDto music) {

        Music documentMusic = API_MAPPER.map(music, Music.class);
        Music documentMusicResponse = repository.save(documentMusic);
        
        return Optional.of(API_MAPPER.map(documentMusicResponse, MusicDto.class));

    }

    @Override
    public Optional<List<MusicDto>> createManyMusicDocument(List<MusicDto> musics) {

        List<Music> documentListMusic = musics.stream().map(m -> API_MAPPER.map(m, Music.class))
        .collect(Collectors.toList());

        System.out.println(musics.size());

        List<Music> documentListMusicResponse = repository.saveAll(documentListMusic);

        List<MusicDto> documentListMusicDtoResponse = documentListMusicResponse.stream()
        .map(m -> API_MAPPER.map(m, MusicDto.class)).collect(Collectors.toList());

        return Optional.of(documentListMusicDtoResponse);
    }   

    @Override
    public Optional<MusicDto> getMusicDocument(String id) {
        
        Optional<Music> OptionalMusic = repository.findById(id);

        if(OptionalMusic.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(API_MAPPER.map(OptionalMusic.get(), MusicDto.class));
    }

    @Override
    public Optional<List<MusicDto>> getAllMusicDocument() {
        
        if(repository.count() <= 0) {
            return Optional.empty();
        }

        return Optional.of(repository.findAll().stream().map(m -> API_MAPPER.map(m,MusicDto.class)).collect(Collectors.toList()));
    } 

    @Override
    public Optional<MusicDto> updateMusicDocument(String id, MusicDto music) {
        
       if(repository.findById(id).isEmpty()) {
           return Optional.empty();
       }

       Music documentMusic =  API_MAPPER.map(music, Music.class);
       documentMusic.setId(id);

       Music documentMusicResponse = repository.save(documentMusic);
       
       return Optional.of(API_MAPPER.map(documentMusicResponse, MusicDto.class));
    }   

    @Override
    public Optional<MusicDto> deleteMusicDocument(String id) {
        
        Optional<Music> documentToDelete = repository.findById(id);

        if(documentToDelete.isEmpty()) {
            return Optional.empty();
        }
        
        repository.deleteById(id);

        return Optional.of(API_MAPPER.map(documentToDelete.get(), MusicDto.class));
    }


}
