package com.blockslicer.game.service;

import com.blockslicer.game.dao1.GamerCredentialsReaderDao;
import com.blockslicer.game.entity1.GamerCredentialsReadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GamerDetailsRetriver implements UserDetailsService {

    @Autowired
    private GamerCredentialsReaderDao gamerCredentialsReaderDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GamerCredentialsReadEntity gamerCredentialsReadEntity = gamerCredentialsReaderDao.findById(username).orElseThrow(() -> new UsernameNotFoundException("user not found exception"));
        return new GamerToUserDetailsMapper(gamerCredentialsReadEntity);
    }
}
