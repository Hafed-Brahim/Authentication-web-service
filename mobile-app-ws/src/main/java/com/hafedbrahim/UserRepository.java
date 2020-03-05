package com.hafedbrahim;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hafedbrahim.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
