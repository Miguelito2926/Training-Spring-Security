package com.ednaldo.springsecurity6.controller;

import com.ednaldo.springsecurity6.dto.FeedDTO;
import com.ednaldo.springsecurity6.dto.TweetDTO;
import com.ednaldo.springsecurity6.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "tweets")
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity postTweet(
            @RequestBody TweetDTO tweetDTO, JwtAuthenticationToken token) {
        tweetService.createTweet(tweetDTO, token);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success Post");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id, JwtAuthenticationToken token) {
        tweetService.deleteTweet(id, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/feed")
    public ResponseEntity<FeedDTO> feed(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        FeedDTO feedDTO = tweetService.listTweets(page, pageSize);
        return ResponseEntity.ok().body(feedDTO);
    }
}
