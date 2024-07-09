package com.blockslicer.game.controller;

import com.blockslicer.game.dto.Auth;
import com.blockslicer.game.entity0.GamerCredentialsWriteEntity;
import com.blockslicer.game.entity0.GamersScoreWriteEntity;
import com.blockslicer.game.service.WriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/write")
public class WriteController {

    @Autowired
    private WriteService writeService;

    @PostMapping("/score")
    public ResponseEntity<GamersScoreWriteEntity> updateScore(@RequestBody GamersScoreWriteEntity gamersScoreWriteEntity) {
        GamersScoreWriteEntity updatedScore = writeService.updateScore(gamersScoreWriteEntity);
        return ResponseEntity.ok(updatedScore);
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody Auth auth) {
        return ResponseEntity.ok(writeService.insertUser(new GamerCredentialsWriteEntity(auth.getUserId(), auth.getPassword())));
    }
}
