package com.code.learnspringboot.service;

import com.code.learnspringboot.dto.FruitDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class FruitsServiceImpl implements FruitsService {

    private static final Map<Long, FruitDto> db = new HashMap<>();

    @Override
    public ResponseEntity<String> save(FruitDto dto) {

        log.info("Attempting to save fruits");

        db.put(dto.getId(), dto);

        log.info("saved fruits to the db");
        log.info("fruits db size {}", db.size());

        return ResponseEntity.status(HttpStatus.CREATED).body("saved successfully");
    }

    @Override
    public ResponseEntity<List<FruitDto>> getAll() {
        log.info("Getting all the fruits data from the database");

        List<FruitDto> fruits = new ArrayList<>(db.values());

        return ResponseEntity.status(HttpStatus.OK).body(fruits);

    }

    @Override
    public ResponseEntity<FruitDto> getById(Long id) {

        log.info("Getting fruit by its id");

        return ResponseEntity.status(HttpStatus.OK).body(db.get(id));
    }

    @Override
    public ResponseEntity<FruitDto> getByName(String name) {

        log.info("Getting fruit by its name");

        Optional<FruitDto> fruit = db.values()
                .stream()
                .filter(f -> f.getName().equalsIgnoreCase(name)).findFirst();

        return fruit.map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElse(null);
    }

    @Override
    public ResponseEntity<FruitDto> update(FruitDto dto, Long id) {

        // If you need to search fruit id in the db, that also can do
        // If object found you can update

        log.info("Attempting to update fruits");

        db.put(dto.getId(), dto);

        log.info("Updated fruits");

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {

        log.info("Attempting to delete the fruit by its id");

        db.remove(id);

        return ResponseEntity.status(HttpStatus.OK).body("successfully deleted");
    }
}
