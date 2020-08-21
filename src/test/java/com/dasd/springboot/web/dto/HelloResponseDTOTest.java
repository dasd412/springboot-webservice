package com.dasd.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class HelloResponseDTOTest {

    @Test
    public void test_lombok(){
        //given
        String name="test";
        int amount=1000;

        //when
    HelloResponseDTO dto=new HelloResponseDTO(name,amount);

    //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

    }
}