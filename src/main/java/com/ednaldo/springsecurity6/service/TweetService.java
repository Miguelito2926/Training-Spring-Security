package com.ednaldo.springsecurity6.service;

import com.ednaldo.springsecurity6.dto.TweetDTO;
import com.ednaldo.springsecurity6.entities.Tweet;
import com.ednaldo.springsecurity6.repositories.TweetRepository;
import com.ednaldo.springsecurity6.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TweetService {

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    public void createTweet(TweetDTO tweetDTO, JwtAuthenticationToken token) {

        var user = userRepository.findAllById(Collections.singleton(UUID.fromString(token.getName())));
        var tweet = new Tweet();
        tweet.setUser(user.get(0));
        tweet.setContent(tweetDTO.content().toString());
        tweetRepository.save(tweet);
    }
}
