package com.blockslicer.game.entity0;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gamersscore")
public class GamersScoreWriteEntity {
    @Id
    @Column(columnDefinition = "TEXT")
    private String userId;

    private Long score;

    private Long blocksSliced;

    public GamersScoreWriteEntity() {
    }

    public GamersScoreWriteEntity(String userId, Long score, Long blocksSliced) {
        this.userId = userId;
        this.score = score;
        this.blocksSliced = blocksSliced;
    }

    public String getUserId() {
        return userId;
    }

    public Long getScore() {
        return score;
    }

    public Long getBlocksSliced() {
        return blocksSliced;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public void setBlocksSliced(Long blocksSliced) {
        this.blocksSliced = blocksSliced;
    }

    // toString method for debugging/logging
    @Override
    public String toString() {
        return "GamersScore{" +
                "userId=" + userId +
                ", score=" + score +
                ", blocksSliced=" + blocksSliced +
                '}';
    }
}
