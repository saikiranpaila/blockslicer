package com.blockslicer.game.service;

import com.blockslicer.game.dao0.GamerCredentialsMasterDao;
import com.blockslicer.game.dao0.GamersScoreMasterDao;
import com.blockslicer.game.entity0.GamerCredentialsWriteEntity;
import com.blockslicer.game.entity0.GamersScoreWriteEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WriteService {

    @Autowired
    private GamersScoreMasterDao gamersScoreMasterDao;

    @Autowired
    private GamerCredentialsMasterDao gamerCredentialsDao;

    @Autowired
    private PasswordEncoder encoder;

    public GamersScoreWriteEntity updateScore(GamersScoreWriteEntity gamersScoreWriteEntity) {
        GamersScoreWriteEntity toUpdate = gamersScoreMasterDao.findById(gamersScoreWriteEntity.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        toUpdate.setScore(gamersScoreWriteEntity.getScore());
        toUpdate.setBlocksSliced(gamersScoreWriteEntity.getBlocksSliced());
        gamersScoreMasterDao.save(toUpdate);
        return toUpdate;
    }

    public Boolean insertUser(GamerCredentialsWriteEntity gamerCredentials) {
        if (gamerCredentialsDao.findById(gamerCredentials.getUserId()).orElse(null) == null) {
            gamerCredentials.setPassword(encoder.encode(gamerCredentials.getPassword()));
            GamersScoreWriteEntity gamersScoreWriteEntity = new GamersScoreWriteEntity(gamerCredentials.getUserId(), 0L, 0L);
            gamerCredentialsDao.save(gamerCredentials);
            gamersScoreMasterDao.save(gamersScoreWriteEntity);
            return true;
        } else {
            return false;
        }
    }
}
