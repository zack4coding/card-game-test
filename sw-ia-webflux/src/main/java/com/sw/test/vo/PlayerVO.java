package com.sw.test.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerVO implements Serializable{

    private String number;
    private String name;
    private List<String> cards;
}
