package com.ipn.swems.backend.repository;

import com.ipn.swems.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author axel_
 */
public interface UserRepository extends MongoRepository<User, String> {

}
