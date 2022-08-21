package com.code.learnspringboot.model;

import com.code.learnspringboot.dto.FruitDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "fruit")
public class FruitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "qty")
    private Integer qty;

    public FruitDto toDto(){
        FruitDto dto = new FruitDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
