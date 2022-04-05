package com.crudapi.prova.crudapi.repository;

import com.crudapi.prova.crudapi.model.Music;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusicRepository extends MongoRepository<Music, String> {
}
