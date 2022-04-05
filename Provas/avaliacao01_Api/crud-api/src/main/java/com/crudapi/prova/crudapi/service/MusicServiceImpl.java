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


    ModelMapper ApiMapper = new ModelMapper();
    private final MusicRepository repository;
    
    MusicServiceImpl (MusicRepository music) {
        this.repository = music;
    }


    @Override
    public Optional<MusicDto> createMusicDocument(MusicDto music) {
        
        Music DocumentMusic = ApiMapper.map(music, Music.class);
        Music DocumentMusicResponse = repository.save(DocumentMusic);
        
        return Optional.of(ApiMapper.map(DocumentMusicResponse, MusicDto.class));

    }

    @Override
    public Optional<MusicDto> getMusicDocument(String id) {
        
        Optional<Music> OptionalMusic = repository.findById(id);

        if(OptionalMusic.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(ApiMapper.map(OptionalMusic, MusicDto.class));
    }

    @Override
    public Optional<MusicDto> updateMusicDocument(String id, MusicDto music) {
        
       if(repository.findById(id).isEmpty()) {
           return Optional.empty();
       }

       Music DocumentMusic =  ApiMapper.map(music, Music.class);
       DocumentMusic.setId(id);

       Music DocumentMusicResponse = repository.save(DocumentMusic);
       
       return Optional.of(ApiMapper.map(DocumentMusicResponse, MusicDto.class));
    }   

    @Override
    public Optional<MusicDto> deleteMusicDocument(String id) {
        
        Optional<Music> DocumentToDelete = repository.findById(id);

        if(DocumentToDelete.isEmpty()) {
            return Optional.empty();
        }
        
        repository.deleteById(id);

        return Optional.of(ApiMapper.map(DocumentToDelete, MusicDto.class));
    }


    @Override
    public List<MusicDto> getTodos() {
        
        return repository.findAll().stream().map(m -> ApiMapper.map(m,MusicDto.class)).collect(Collectors.toList());
    }
    
}
