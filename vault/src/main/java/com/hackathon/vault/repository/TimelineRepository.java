package com.hackathon.vault.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.vault.entity.Timeline;

@Repository
public interface TimelineRepository extends MongoRepository<Timeline, String> {

}