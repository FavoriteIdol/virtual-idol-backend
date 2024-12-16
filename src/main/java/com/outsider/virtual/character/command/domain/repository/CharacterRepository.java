package com.outsider.virtual.character.command.domain.repository;

import com.outsider.virtual.character.command.domain.aggregate.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByUserId(Long userId);
    Optional<Character> findByUserIdAndIsActiveTrue(Long userId);
    boolean existsByUserId(Long userId);
} 