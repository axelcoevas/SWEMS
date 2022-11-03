package com.ipn.swems.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ipn.swems.backend.model.Project;

/**
 *
 * @author axel_
 */
public interface ProjectRepository extends MongoRepository<Project, String> {

}
