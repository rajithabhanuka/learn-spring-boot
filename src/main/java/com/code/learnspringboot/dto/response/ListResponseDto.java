package com.code.learnspringboot.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ListResponseDto<T> implements ResponseDto {

    private String message;

    private List<T> data;

}
