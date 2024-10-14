package com.outsider.virtual.stage.command.domain.aggregate;

import jakarta.persistence.*;

@Entity
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TerrainType terrain; // 무대의 지형 설정 (예: 맨땅, 바다, 구름)

    @Enumerated(EnumType.STRING)
    private SkyType sky; // 무대의 스카이 설정 (예: 맑음, 흐림, 밤하늘 등)

    @Enumerated(EnumType.STRING)
    private ThemeType theme; // 무대의 테마 설정 (예: 사막, 지형, 우주 등)

    @Enumerated(EnumType.STRING)
    private SpecialEffectType specialEffect; // 무대의 특수효과 설정 (예: 눈, 비, 불꽃 등)

    private Long userId; // 제작자 이름

    // 기본 생성자
    public Stage() {}

    // 모든 필드를 포함한 생성자
    public Stage(String name, TerrainType terrain, SkyType sky, ThemeType theme, SpecialEffectType specialEffect, Long userId) {
        this.name = name;
        this.terrain = terrain;
        this.sky = sky;
        this.theme = theme;
        this.specialEffect = specialEffect;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TerrainType getTerrain() {
        return terrain;
    }

    public void setTerrain(TerrainType terrain) {
        this.terrain = terrain;
    }

    public SkyType getSky() {
        return sky;
    }

    public void setSky(SkyType sky) {
        this.sky = sky;
    }

    public ThemeType getTheme() {
        return theme;
    }

    public void setTheme(ThemeType theme) {
        this.theme = theme;
    }

    public SpecialEffectType getSpecialEffect() {
        return specialEffect;
    }

    public void setSpecialEffect(SpecialEffectType specialEffect) {
        this.specialEffect = specialEffect;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long creatorName) {
        this.userId = creatorName;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", terrain=" + terrain +
                ", sky=" + sky +
                ", theme=" + theme +
                ", specialEffect=" + specialEffect +
                ", userId=" + userId +
                '}';
    }
}
