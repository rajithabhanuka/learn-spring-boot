package com.code.learnspringboot.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SuccessResponseDto<T> implements ResponseDto{

    private String message;

    private T data;
}
