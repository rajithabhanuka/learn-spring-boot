package com.code.learnspringboot.repository;

import com.code.learnspringboot.model.FruitEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FruitRepository extends CrudRepository<FruitEntity, Long> {

    Optional<FruitEntity> findByName(String name);

}
