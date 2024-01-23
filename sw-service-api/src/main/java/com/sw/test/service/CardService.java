package com.sw.test.service;

import com.sw.test.dto.PlayerDTO;

import java.util.List;

public interface CardService {


    String findWinner(List<PlayerDTO> players);

    List<String> alphanumericWinRule(List<String> cards);

    boolean compareTo(List<String> cardsA, List<String> cardsB);

    String alphaToNumeric(String card);

    String symbolToNumeric(String card);
}
