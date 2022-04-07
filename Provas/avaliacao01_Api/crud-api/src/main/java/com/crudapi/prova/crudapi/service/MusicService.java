package com.crudapi.prova.crudapi.service;

import java.util.List;
import java.util.Optional;

import com.crudapi.prova.crudapi.shared.MusicDto;

public interface MusicService {
    // crud

    Optional<MusicDto> createMusicDocument (MusicDto music);
    Optional<List<MusicDto>> createManyMusicDocument (List<MusicDto> musics);
    Optional<MusicDto> getMusicDocument (String id);
    Optional<MusicDto> updateMusicDocument (String id, MusicDto music);
    Optional<MusicDto> deleteMusicDocument (String id);
    Optional<List<MusicDto>> getAllMusicDocument ();
}
