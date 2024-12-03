package com.outsider.virtual.song.command.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.outsider.virtual.file.command.application.service.MinioService;
import com.outsider.virtual.song.command.domain.aggregate.Song;
import com.outsider.virtual.song.command.domain.repository.SongRepository;
import com.outsider.virtual.user.exception.NotMineException;

@Service
@Transactional
public class SongDeleteService {
    private final SongRepository songRepository;
    private final MinioService minioService;

    public SongDeleteService(SongRepository songRepository, MinioService minioService) {
        this.songRepository = songRepository;
        this.minioService = minioService;
    }

    public void delete(Long userId, Long songId) {
        Song song = songRepository.findById(songId)
            .orElseThrow(() -> new IllegalArgumentException("Song not found"));

        // 권한 체크
        if (!song.getArtistId().equals(userId)) {
            throw new NotMineException();
        }

        // 파일 URL 저장
        String fileUrl = song.getUrl();

        try {
            // 먼저 Song 엔티티 삭제
            songRepository.delete(song);
            
            // 파일 삭제
            if (fileUrl != null) {
                String filename = minioService.extractFilenameFromUrl(fileUrl);
                minioService.deleteFile(filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete song and file", e);
        }
    }
} 