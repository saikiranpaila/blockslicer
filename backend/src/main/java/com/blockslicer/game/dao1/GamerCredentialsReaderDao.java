package com.blockslicer.game.dao1;


import com.blockslicer.game.entity1.GamerCredentialsReadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerCredentialsReaderDao extends JpaRepository<GamerCredentialsReadEntity, String> {
}
