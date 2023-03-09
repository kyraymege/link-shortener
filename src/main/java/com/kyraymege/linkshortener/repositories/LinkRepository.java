package com.kyraymege.linkshortener.repositories;

import com.kyraymege.linkshortener.models.Link;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends MongoRepository<Link, ObjectId> {
    @Query("{code:'?0'}")
    public Link findByCode(String code);

    @ExistsQuery("{code:'?0'}")
    Boolean existLinkByCode(String code);
}
