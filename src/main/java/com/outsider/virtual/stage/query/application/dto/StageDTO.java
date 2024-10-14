package com.outsider.virtual.stage.query.application.dto;

import com.outsider.virtual.stage.command.domain.aggregate.SkyType;
import com.outsider.virtual.stage.command.domain.aggregate.SpecialEffectType;
import com.outsider.virtual.stage.command.domain.aggregate.TerrainType;
import com.outsider.virtual.stage.command.domain.aggregate.ThemeType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StageDTO {
    private Long id;
    private String name;
    private String terrain;
    private String sky;
    private String theme;
    private String specialEffect;
    private String userName;
    public StageDTO(Long id, String name, TerrainType terrain, SkyType sky, ThemeType theme, SpecialEffectType specialEffect, String userName) {
        this.id = id;
        this.name = name;
        this.terrain = terrain != null ? terrain.name() : null;
        this.sky = sky != null ? sky.name() : null;
        this.theme = theme != null ? theme.name() : null;
        this.specialEffect = specialEffect != null ? specialEffect.name() : null;
        this.userName = userName;
    }
}
