package com.code.learnspringboot.service;

import com.code.learnspringboot.dto.FruitDto;
import com.code.learnspringboot.dto.response.ResponseDto;
import com.code.learnspringboot.dto.response.SuccessResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FruitsService {

    ResponseEntity<ResponseDto> save(FruitDto dto);

    ResponseEntity<ResponseDto> getAll();

    ResponseEntity<ResponseDto> getById(Long id);

    ResponseEntity<ResponseDto> getByName(String name);

    ResponseEntity<ResponseDto> update(FruitDto dto, Long id);

    ResponseEntity<ResponseDto> delete(Long id);
}
