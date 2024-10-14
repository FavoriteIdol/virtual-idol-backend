package com.outsider.virtual.stage.command.application.dto;

import com.outsider.virtual.stage.command.domain.aggregate.SkyType;
import com.outsider.virtual.stage.command.domain.aggregate.SpecialEffectType;
import com.outsider.virtual.stage.command.domain.aggregate.TerrainType;
import com.outsider.virtual.stage.command.domain.aggregate.ThemeType;
import lombok.Data;

@Data
public class StageCreateDTO {
    private String name;
    private TerrainType terrain;
    private SkyType sky;
    private ThemeType theme;
    private SpecialEffectType specialEffect;
    private Long userId;
}
