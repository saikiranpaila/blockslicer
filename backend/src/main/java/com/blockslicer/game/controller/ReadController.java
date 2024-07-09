package com.blockslicer.game.controller;

import com.blockslicer.game.dto.Auth;
import com.blockslicer.game.entity1.GamersScoreReadEntity;
import com.blockslicer.game.service.JwtService;
import com.blockslicer.game.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/read")
public class ReadController {

    @Autowired
    private ReadService scoreService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/score")
    public ResponseEntity<GamersScoreReadEntity> getScore(@RequestParam String userId) {
        GamersScoreReadEntity toReturn = scoreService.getUserScore(userId);
        if (toReturn == null) {
            toReturn = new GamersScoreReadEntity("", -1L, -1L);
        }
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping("/rank")
    public ResponseEntity<Long> getRank(@RequestParam String userId) {
        return ResponseEntity.ok(scoreService.getRank(userId));
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<GamersScoreReadEntity>> getLeaderBoard(@RequestParam(name = "userId", defaultValue = "", required = false) String userId) {
        List<GamersScoreReadEntity> list = scoreService.getLeaderBoard(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/unique")
    public ResponseEntity<Boolean> isUnique(@RequestParam(name = "userId", required = true) String userId) {
        return ResponseEntity.ok(scoreService.isUnique(userId));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody Auth auth) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUserId(), auth.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.generateToken(auth.getUserId()));
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
