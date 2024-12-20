package com.outsider.virtual.stage.command.application.dto;

import com.outsider.virtual.stage.command.domain.aggregate.SkyType;
import com.outsider.virtual.stage.command.domain.aggregate.SpecialEffectType;
import com.outsider.virtual.stage.command.domain.aggregate.TerrainType;
import com.outsider.virtual.stage.command.domain.aggregate.ThemeType;
import lombok.Data;

@Data
public class StageUpdateDTO {
    private String name;
    private Integer terrain;
    private Integer sky;
    private Integer theme;
    private Integer specialEffect;
    private String img;
}
