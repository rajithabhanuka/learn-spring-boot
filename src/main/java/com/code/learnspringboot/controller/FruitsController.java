package com.code.learnspringboot.controller;

import com.code.learnspringboot.dto.FruitDto;
import com.code.learnspringboot.service.FruitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("fruits")
public class FruitsController {

    private final FruitsService fruitsService;

    public FruitsController(FruitsService fruitsService) {
        this.fruitsService = fruitsService;
    }

    /**
     * @param dto contains all the fruits details to be saved
     * @return response entity with the status
     */
    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid FruitDto dto) {

        return fruitsService.save(dto);

    }

    /**
     * @return all the fruits that saved in the db
     */
    @GetMapping
    public ResponseEntity<List<FruitDto>> getAll() {

        return fruitsService.getAll();

    }

    /**
     * @param id for fruits
     * @return fruit data for the given id
     */
    @GetMapping(value = "/{Id}")
    public ResponseEntity<FruitDto> getById(@PathVariable(value = "Id") Long id) {

        return fruitsService.getById(id);

    }

    /**
     * @param name for fruits
     * @return fruit data for the given name
     */
    @GetMapping(value = "/by-name")
    public ResponseEntity<FruitDto> getByName(@RequestParam(value = "name") String name) {

        return fruitsService.getByName(name);

    }

    /**
     * @param dto fruit object to be updated
     * @param id  for fruit
     * @return updated details
     */
    @PutMapping(value = "/{Id}")
    public ResponseEntity<FruitDto> update(@RequestBody FruitDto dto,
                                           @PathVariable(value = "Id") Long id) {

        return fruitsService.update(dto, id);

    }

    /**
     * @param id for fruit
     * @return message as a text
     */
    @DeleteMapping(value = "/{Id}")
    public ResponseEntity<String> delete(@PathVariable(value = "Id") Long id) {

        return fruitsService.delete(id);

    }


}
