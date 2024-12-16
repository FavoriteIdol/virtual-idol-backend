package com.outsider.virtual.character.query.application.service;

import com.outsider.virtual.character.command.domain.aggregate.Character;
import com.outsider.virtual.character.command.domain.repository.CharacterRepository;
import com.outsider.virtual.character.query.application.dto.CharacterDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CharacterQueryService {

    private final CharacterRepository characterRepository;

    public CharacterQueryService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<CharacterDTO> getCharactersByUserId(Long userId) {
        return characterRepository.findByUserId(userId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public boolean hasCharacter(Long userId) {
        return characterRepository.existsByUserId(userId);
    }

    private CharacterDTO convertToDTO(Character character) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(character.getId());
        dto.setName(character.getName());
        dto.setDescription(character.getDescription());
        dto.setImageUrl(character.getImageUrl());
        dto.setActive(character.isActive());
        return dto;
    }
} 