package com.pelo.boipelozuma_gadsleaderboardmobileapplication.models.learner;

import com.pelo.boipelozuma_gadsleaderboardmobileapplication.models.LeaderType;

public class Learner {
    private String name;
    private int score;
    private String country;
    private String badgeUrl;
    private LeaderType leaderType;

    public Learner(String name, int score, String country, String badgeUrl, LeaderType leaderType) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
        this.leaderType = leaderType;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public LeaderType getLeaderType() {
        return leaderType;
    }
}

