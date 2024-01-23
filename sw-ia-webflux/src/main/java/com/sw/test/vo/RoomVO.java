package com.sw.test.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomVO implements Serializable{

    private String winner;
    private List<String> deck;
    private List<PlayerVO> players;

    public PlayerVO whoIsWinner() {

        return null;
    }
}
