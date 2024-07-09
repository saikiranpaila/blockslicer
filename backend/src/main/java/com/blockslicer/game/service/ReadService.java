package com.blockslicer.game.service;

import com.blockslicer.game.dao1.GamerCredentialsReaderDao;
import com.blockslicer.game.dao1.GamersScoreReaderDao;
import com.blockslicer.game.entity1.GamerCredentialsReadEntity;
import com.blockslicer.game.entity1.GamersScoreReadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional(value = "transactionManager2",readOnly = true)
@Service
public class ReadService {
    @Autowired
    private GamersScoreReaderDao gamersScoreReadDao;

    @Autowired
    private GamerCredentialsReaderDao gamerCredentialsReaderDao;

    public GamersScoreReadEntity getUserScore(String userId) {
        GamersScoreReadEntity gamersScoreEntity = gamersScoreReadDao.findById(userId).orElse(null);
        return gamersScoreEntity;
    }

    public List<GamersScoreReadEntity> getLeaderBoard(String userId) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("score").descending());
        List<GamersScoreReadEntity> list = gamersScoreReadDao.findAll(pageable).getContent();
        if (!Objects.equals(userId, "")) {
            GamersScoreReadEntity currentGamerScore = gamersScoreReadDao.findById(userId).orElse(null);
            list.add(currentGamerScore);
        }
        return list;
    }

    public Boolean isUnique(String userId) {
        GamerCredentialsReadEntity gamersScoreEntity = gamerCredentialsReaderDao.findById(userId).orElse(null);
        return gamersScoreEntity == null;
    }

    public Long getRank(String userId) {
        return gamersScoreReadDao.getGamerRowNumberById(userId);
    }
}
