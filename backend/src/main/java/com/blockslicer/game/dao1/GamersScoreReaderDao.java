package com.blockslicer.game.dao1;

import com.blockslicer.game.entity1.GamersScoreReadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GamersScoreReaderDao extends JpaRepository<GamersScoreReadEntity, String> {
    @Query(value = "SELECT rownum FROM (SELECT ROW_NUMBER() OVER (ORDER BY g.score DESC) AS rownum, g.userId FROM gamersscore g) AS subquery WHERE subquery.userId = :userId", nativeQuery = true)
    Long getGamerRowNumberById(@Param("userId") String userId);
}
