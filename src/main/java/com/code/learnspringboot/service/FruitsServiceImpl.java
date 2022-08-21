package com.code.learnspringboot.service;

import com.code.learnspringboot.dto.FruitDto;
import com.code.learnspringboot.dto.response.ErrorResponseDto;
import com.code.learnspringboot.dto.response.ListResponseDto;
import com.code.learnspringboot.dto.response.ResponseDto;
import com.code.learnspringboot.dto.response.SuccessResponseDto;
import com.code.learnspringboot.model.FruitEntity;
import com.code.learnspringboot.repository.FruitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class FruitsServiceImpl implements FruitsService {

    private static final Map<Long, FruitDto> db = new HashMap<>();

    private final FruitRepository fruitRepository;

    public FruitsServiceImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public ResponseEntity<ResponseDto> save(FruitDto dto) {

        try {

            log.info("Attempting to save fruits");

            FruitEntity saved = fruitRepository.save(dto.toEntity());

            log.info("saved fruits to the db");

            return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponseDto.
                    builder().
                    data(saved.toDto()).
                    message("successfully saved")
                    .build());

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDto.
                    builder()
                    .errorCode("ERR001")
                    .errorMessage("Something went wrong while saving fruit")
                    .variables("Fruits ID ".concat(String.valueOf(dto.getId())))
                    .build());
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getAll() {

        try {
            log.info("Getting all the fruits data from the database");

            List<FruitDto> fruits = new ArrayList<>();

            fruitRepository.findAll().forEach(s -> fruits.add(s.toDto()));

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ListResponseDto
                            .builder()
                            .message("Successful")
                            .data(Collections.singletonList(fruits)).build());

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDto.
                    builder()
                    .errorCode("ERR002")
                    .errorMessage("Something went wrong while getting all the fruits")
                    .variables("")
                    .build());
        }

    }

    @Override
    public ResponseEntity<ResponseDto> getById(Long id) {

        try {
            log.info("Getting fruit by its id");

            FruitEntity entity = fruitRepository.findById(id).orElseThrow(() -> new Exception("Not Found"));

            return ResponseEntity.status(HttpStatus.OK).body(SuccessResponseDto
                    .builder()
                    .message("Successfully found")
                    .data(entity.toDto())
                    .build());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDto.
                    builder()
                    .errorCode("ERR003")
                    .errorMessage("Record Not Found")
                    .variables("Fruit Id".concat(String.valueOf(id)))
                    .build());
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getByName(String name) {

        try {
            log.info("Getting fruit by its name");

            FruitEntity entity = fruitRepository.findByName(name).orElseThrow(() -> new Exception("Not Found"));

            return ResponseEntity.status(HttpStatus.OK).body(SuccessResponseDto
                    .builder()
                    .message("Successfully found")
                    .data(entity.toDto())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDto.
                    builder()
                    .errorCode("ERR003")
                    .errorMessage("Record Not Found")
                    .variables("Fruit name".concat(name))
                    .build());
        }
    }

    @Override
    public ResponseEntity<ResponseDto> update(FruitDto dto, Long id) {

        // If you need to search fruit id in the db, that also can do
        // If object found you can update

        try {

            log.info("Attempting to update fruits");

            FruitEntity entity = fruitRepository.findById(id).orElseThrow(() -> new Exception("Not Found"));

            entity = dto.toEntity();

            FruitEntity saved = fruitRepository.save(entity);

            log.info("Updated fruits");

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(SuccessResponseDto.
                    builder().
                    data(saved.toDto()).
                    message("successfully updated")
                    .build());

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDto.
                    builder()
                    .errorCode("ERR004")
                    .errorMessage("Something went wrong while updating fruit")
                    .variables("Fruits ID ".concat(String.valueOf(dto.getId())))
                    .build());
        }

    }

    @Override
    public ResponseEntity<ResponseDto> delete(Long id) {

        log.info("Attempting to delete the fruit by its id");

        fruitRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(SuccessResponseDto.
                builder().
                data(null).
                message("successfully deleted")
                .build());
    }
}
