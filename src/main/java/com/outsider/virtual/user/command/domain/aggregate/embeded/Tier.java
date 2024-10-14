package com.outsider.virtual.user.command.domain.aggregate.embeded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Tier {

    @Column(name = "tier_name")
    private String name;

    @Column(name = "tier_level")
    private int level; // 1 to 5, applicable for SEER, NOVICE, ORACLE, PROPHET

    // Constructors
    public Tier() {
        this.name = "UNRANKED";
        this.level = 0;
    }

    public Tier(String name, int level) {
        this.name = name;
        this.level = level;
    }

    // Factory method to determine tier based on points and betting activity
    public static Tier getTierByPointsAndBets(int points, int numberOfBets) {
        int minimumBetsRequired = 1;
        if (points < 0 || numberOfBets < 0) {
            throw new IllegalArgumentException("Points and number of bets cannot be negative");
        }

        // Check if the user has made the minimum required bets, otherwise they are unranked
        if (numberOfBets < minimumBetsRequired) {
            return new Tier("UNRANKED", 0);
        }

        // Determine the tier based on points if the betting requirement is met
        if (points < 100) {
            return new Tier("SEER", 1);
        } else if (points < 200) {
            return new Tier("SEER", 2);
        } else if (points < 300) {
            return new Tier("SEER", 3);
        } else if (points < 400) {
            return new Tier("SEER", 4);
        } else if (points < 500) {
            return new Tier("SEER", 5);
        } else if (points < 600) {
            return new Tier("NOVICE", 1);
        } else if (points < 700) {
            return new Tier("NOVICE", 2);
        } else if (points < 800) {
            return new Tier("NOVICE", 3);
        } else if (points < 900) {
            return new Tier("NOVICE", 4);
        } else if (points < 1000) {
            return new Tier("NOVICE", 5);
        } else if (points < 1500) {
            return new Tier("ORACLE", 1);
        } else if (points < 2000) {
            return new Tier("ORACLE", 2);
        } else if (points < 2500) {
            return new Tier("ORACLE", 3);
        } else if (points < 3000) {
            return new Tier("ORACLE", 4);
        } else if (points < 3500) {
            return new Tier("ORACLE", 5);
        } else if (points < 4000) {
            return new Tier("PROPHET", 1);
        } else if (points < 4500) {
            return new Tier("PROPHET", 2);
        } else if (points < 5000) {
            return new Tier("PROPHET", 3);
        } else if (points < 6000) {
            return new Tier("PROPHET", 4);
        } else if (points < 7000) {
            return new Tier("PROPHET", 5);
        } else {
            return new Tier("NOSTRADAMUS", 1);
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return name + " Level " + level;
    }

    // equals, hashCode 메소드 구현 필요
}
