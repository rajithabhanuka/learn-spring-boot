package com.code.learnspringboot.dto;

import com.code.learnspringboot.model.FruitEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class FruitDto {

    private Long id;

    @Pattern(regexp = "^([^0-9]*)$", message = "Name should not contains a numeric value")
    private String name;

    @Pattern(regexp = "^([^0-9]*)$", message = "Country should not contains a numeric value")
    private String country;

    @Min(value = 0, message = "Qty should be a Positive number")
    private Integer qty;

    public FruitEntity toEntity(){
        FruitEntity entity = new FruitEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

}
