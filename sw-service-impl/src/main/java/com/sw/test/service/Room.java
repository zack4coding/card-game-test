package com.sw.test.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room implements Serializable{

    private String id;
    private String name;
    private String winner;
    private List<String> deck;
    private List<Player> players;

}
