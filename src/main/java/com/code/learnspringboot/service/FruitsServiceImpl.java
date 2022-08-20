package com.code.learnspringboot.service;

import com.code.learnspringboot.dto.FruitDto;
import com.code.learnspringboot.dto.response.ErrorResponseDto;
import com.code.learnspringboot.dto.response.ListResponseDto;
import com.code.learnspringboot.dto.response.ResponseDto;
import com.code.learnspringboot.dto.response.SuccessResponseDto;
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
    public ResponseEntity<ResponseDto> save(FruitDto dto) {

        try {

            log.info("Attempting to save fruits");

            db.put(dto.getId(), dto);

            log.info("saved fruits to the db");
            log.info("fruits db size {}", db.size());

            return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponseDto.
                    builder().
                    data(dto).
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

            List<FruitDto> fruits = new ArrayList<>(db.values());

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

            return ResponseEntity.status(HttpStatus.OK).body(SuccessResponseDto
                    .builder()
                    .message("Successfully found")
                    .data(db.get(id))
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
