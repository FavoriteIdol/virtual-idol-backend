package com.outsider.virtual.stage.command.application.dto;

import com.outsider.virtual.stage.command.domain.aggregate.SkyType;
import com.outsider.virtual.stage.command.domain.aggregate.SpecialEffectType;
import com.outsider.virtual.stage.command.domain.aggregate.TerrainType;
import com.outsider.virtual.stage.command.domain.aggregate.ThemeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StageCreateDTO {
    private String name;
    private Integer terrain;
    private Integer sky;
    private Integer theme;
    private Integer specialEffect;
    @Schema(description = "URL of the image associated with the stage.", example = "https://example.com/image.jpg")
    private String img;
    @Schema(hidden = true)
    private Long userId;
}
