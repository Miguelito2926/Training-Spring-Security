package com.ednaldo.springsecurity6.service;

import com.ednaldo.springsecurity6.dto.FeedDTO;
import com.ednaldo.springsecurity6.dto.FeedItemDTO;
import com.ednaldo.springsecurity6.dto.TweetDTO;
import com.ednaldo.springsecurity6.entities.Role;
import com.ednaldo.springsecurity6.entities.Tweet;
import com.ednaldo.springsecurity6.repositories.TweetRepository;
import com.ednaldo.springsecurity6.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
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

    public void deleteTweet(Long id, JwtAuthenticationToken token) {

        var user = userRepository.findAllById(Collections.singleton(UUID.fromString(token.getName())));
        // Verifica se o tweet existe no repositório
        var tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tweet não encontrado"));

        var isAdmin = user.get(0).getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        // Verifica se o usuário autenticado é o dono do tweet
        if (isAdmin ||tweet.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            // Deleta o tweet
            tweetRepository.deleteById(id);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para deletar este tweet");
        }
    }

    public FeedDTO listTweets(int page, int pageSize) {
       var tweets = tweetRepository.findAll(
               PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationTimestamp"))
               .map(tweet -> new FeedItemDTO(tweet.getTweetId(),
                       tweet.getContent(),
                       tweet.getUser().getUsername())
               );

       return new FeedDTO(
               tweets.getContent(),
               page,
               pageSize,
               tweets.getTotalPages(),
               tweets.getTotalElements()
       );
    }
}
