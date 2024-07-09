package com.blockslicer.game.dao0;

import com.blockslicer.game.entity0.GamerCredentialsWriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerCredentialsMasterDao extends JpaRepository<GamerCredentialsWriteEntity, String> {
}
