package com.outsider.virtual.character.command.domain.aggregate;

import com.outsider.virtual.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "characters")
public class Character extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long id;

    @Column(name = "character_name")
    private String name;

    @Column(name = "character_description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "model_url")
    private String modelUrl;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "user_id")
    private Long userId;
} 