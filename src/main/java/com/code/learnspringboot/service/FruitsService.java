package com.code.learnspringboot.service;

import com.code.learnspringboot.dto.FruitDto;
import com.code.learnspringboot.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FruitsService {

    ResponseEntity<ResponseDto> save(FruitDto dto);

    ResponseEntity<ResponseDto> getAll();

    ResponseEntity<ResponseDto> getById(Long id);

    ResponseEntity<FruitDto> getByName(String name);

    ResponseEntity<FruitDto> update(FruitDto dto, Long id);

    ResponseEntity<String> delete(Long id);
}
