package com.code.learnspringboot.controller;

import com.code.learnspringboot.dto.FruitDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("fruits")
public class FruitsController {

    private static final Map<Long, FruitDto> db = new HashMap<>();

    /**
     * @param dto contains all the fruits details to be saved
     * @return response entity with the status
     */
    @PostMapping
    public ResponseEntity<String> save(@RequestBody FruitDto dto) {

        log.info("Attempting to save fruits");

        db.put(dto.getId(), dto);

        log.info("saved fruits to the db");
        log.info("fruits db size {}", db.size());

        return ResponseEntity.status(HttpStatus.CREATED).body("saved successfully");
    }

    /**
     * @return all the fruits that saved in the db
     */
    @GetMapping
    public ResponseEntity<List<FruitDto>> getAll() {

        log.info("Getting all the fruits data from the database");

        List<FruitDto> fruits = new ArrayList<>(db.values());

        return ResponseEntity.status(HttpStatus.OK).body(fruits);

    }

    /**
     * @param id for fruits
     * @return fruit data for the given id
     */
    @GetMapping(value = "/{Id}")
    public ResponseEntity<FruitDto> getById(@PathVariable(value = "Id") Long id) {

        log.info("Getting fruit by its id");

        return ResponseEntity.status(HttpStatus.OK).body(db.get(id));

    }

    /**
     * @param name for fruits
     * @return fruit data for the given name
     */
    @GetMapping(value = "/by-name")
    public ResponseEntity<FruitDto> getByName(@RequestParam(value = "name") String name) {

        log.info("Getting fruit by its name");

        Optional<FruitDto> fruit = db.values()
                .stream()
                .filter(f -> f.getName().equalsIgnoreCase(name)).findFirst();

        return fruit.map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElse(null);

    }

    /**
     *
     * @param dto fruit object to be updated
     * @param id for fruit
     * @return updated details
     */
    @PutMapping(value = "/{Id}")
    public ResponseEntity<FruitDto> update(@RequestBody FruitDto dto,
                                           @PathVariable(value = "Id") Long id) {

        // If you need to search fruit id in the db, that also can do
        // If object found you can update

        log.info("Attempting to update fruits");

        db.put(dto.getId(), dto);

        log.info("Updated fruits");

        return ResponseEntity.status(HttpStatus.OK).body(dto);

    }

    /**
     *
     * @param id for fruit
     * @return message as a text
     */
    @DeleteMapping(value = "/{Id}")
    public ResponseEntity<String> delete(@PathVariable(value = "Id") Long id){

        log.info("Attempting to delete the fruit by its id");

        db.remove(id);

        return ResponseEntity.status(HttpStatus.OK).body("successfully deleted");
    }


}
