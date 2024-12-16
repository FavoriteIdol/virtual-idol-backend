package com.outsider.virtual.character.command.application.service;

import com.outsider.virtual.character.command.application.dto.CharacterCreateDTO;
import com.outsider.virtual.character.command.application.dto.CharacterUpdateDTO;
import com.outsider.virtual.character.command.domain.aggregate.Character;
import com.outsider.virtual.character.command.domain.repository.CharacterRepository;
import com.outsider.virtual.file.command.domain.service.FileUploadService;
import com.outsider.virtual.user.exception.NotExistException;
import com.outsider.virtual.user.exception.NotMineException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CharacterCommandService {

    private final CharacterRepository characterRepository;
    private final FileUploadService fileUploadService;

    public CharacterCommandService(CharacterRepository characterRepository,
                                 FileUploadService fileUploadService) {
        this.characterRepository = characterRepository;
        this.fileUploadService = fileUploadService;
    }

    public Long create(Long userId, CharacterCreateDTO dto) throws Exception {
        String imageUrl = fileUploadService.uploadFile(dto.getImage());
        String modelUrl = fileUploadService.uploadFile(dto.getModel());

        characterRepository.findByUserId(userId).forEach(c -> c.setActive(false));

        Character character = new Character();
        character.setName(dto.getName());
        character.setDescription(dto.getDescription());
        character.setImageUrl(imageUrl);
        character.setModelUrl(modelUrl);
        character.setUserId(userId);
        character.setActive(true);

        characterRepository.save(character);
        return character.getId();
    }

    public void update(Long userId, Long characterId, CharacterUpdateDTO dto) throws Exception {
        Character character = characterRepository.findById(characterId)
            .orElseThrow(NotExistException::new);

        if (!character.getUserId().equals(userId)) {
            throw new NotMineException();
        }

        if (dto.getName() != null) {
            character.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            character.setDescription(dto.getDescription());
        }
        if (dto.getImage() != null) {
            String imageUrl = fileUploadService.uploadFile(dto.getImage());
            character.setImageUrl(imageUrl);
        }
        if (dto.getModel() != null) {
            String modelUrl = fileUploadService.uploadFile(dto.getModel());
            character.setModelUrl(modelUrl);
        }
        if (dto.getIsActive() != null) {
            if (dto.getIsActive()) {
                characterRepository.findByUserId(userId).forEach(c -> {
                    if (!c.getId().equals(characterId)) {
                        c.setActive(false);
                    }
                });
            }
            character.setActive(dto.getIsActive());
        }

        characterRepository.save(character);
    }

    public void delete(Long userId, Long characterId) {
        Character character = characterRepository.findById(characterId)
            .orElseThrow(NotExistException::new);

        if (!character.getUserId().equals(userId)) {
            throw new NotMineException();
        }

        characterRepository.delete(character);
    }
} 