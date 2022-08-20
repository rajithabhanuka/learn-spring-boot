package com.code.learnspringboot.service;

import com.code.learnspringboot.dto.FruitDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FruitsService {

    ResponseEntity<String> save(FruitDto dto);

    ResponseEntity<List<FruitDto>> getAll();

    ResponseEntity<FruitDto> getById(Long id);

    ResponseEntity<FruitDto> getByName(String name);

    ResponseEntity<FruitDto> update(FruitDto dto, Long id);

    ResponseEntity<String> delete(Long id);
}
