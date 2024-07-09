package com.blockslicer.game.dao0;

import com.blockslicer.game.entity0.GamersScoreWriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GamersScoreMasterDao extends JpaRepository<GamersScoreWriteEntity, String> {
    @Query(value = "SELECT rownum FROM (SELECT ROW_NUMBER() OVER (ORDER BY g.score DESC) AS rownum, g.userId FROM gamersscore g) AS subquery WHERE subquery.userId = :userId", nativeQuery = true)
    Long getUserRowNumberById(@Param("userId") String userId);
}
