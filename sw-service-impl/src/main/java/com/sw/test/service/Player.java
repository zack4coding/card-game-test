package com.sw.test.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.websocket.Session;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player implements Serializable{

    private String number;
    private String name;
    private List<String> cards;

    @JsonIgnore
    private Session session;
}
