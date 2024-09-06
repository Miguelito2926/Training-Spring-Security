package com.ednaldo.springsecurity6.repositories;

import com.ednaldo.springsecurity6.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
