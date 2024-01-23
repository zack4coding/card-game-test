package com.sw.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO implements Serializable{

    private String number;
    private String name;
    private List<String> cards;
}
