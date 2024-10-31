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
    private Integer terrain;
    private Integer sky;
    private Integer theme;
    private Integer specialEffect;
    private String userName;
    private Long userId;
    private String img;

}
