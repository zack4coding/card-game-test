package com.sw.test.service;

import com.sw.test.dto.PlayerDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService{


    public String findWinner(List<PlayerDTO> players){
        return null;
    }

    public List<String> alphanumericWinRule(List<String> cards){

       TreeMap<String, List<String>> groupedCard = cards.stream().collect(Collectors.groupingBy(card -> {
           return alphaToNumeric(card);
       }, TreeMap::new
               , Collectors.toList()));


        NavigableMap descendingMap = groupedCard.descendingMap();

        List<String> mostAndLargest  = new ArrayList<>();
        for(Object entry: descendingMap.values()) {
           if ( ((List)entry).size() > mostAndLargest.size() ) {
               mostAndLargest = (List)entry;
           }
        }

        return mostAndLargest;
    }

    @Override
    public boolean compareTo(List<String> cardsNew, List<String> cardsHighest) {
        if (cardsNew.size() > cardsHighest.size()) {
            return true;
        }

        if (Integer.valueOf(alphaToNumeric(cardsNew.get(0))) >
                Integer.valueOf(alphaToNumeric(cardsHighest.get(0)))) {
            return true;
        }

        OptionalInt newMax = cardsNew.stream().mapToInt(card -> {
            return Integer.valueOf(symbolToNumeric(card));
        }).max();

        OptionalInt winnerMax = cardsHighest.stream().mapToInt(card -> {
            return Integer.valueOf(symbolToNumeric(card));
        }).max();

        if (newMax.getAsInt() > winnerMax.getAsInt()) {
            return true;
        }

        return false;
    }

    public String symbolToNumeric(String card) {
        String symbol = ((String)card).trim().substring(1,2);
        if ("@".equals(symbol)) {
            return "1";
        }
        if ("#".equals(symbol)) {
            return "2";
        }
        if ("^".equals(symbol)) {
            return "3";
        }
        if ("*".equals(symbol)) {
            return "4";
        }
        return symbol;
    }

    public String alphaToNumeric(String card) {
        String alphanumeric = ((String)card).trim().substring(0,1);
        if ("J".equals(alphanumeric)) {
            return "11";
        }
        if ("Q".equals(alphanumeric)) {
            return "12";
        }
        if ("K".equals(alphanumeric)) {
            return "13";
        }
        if ("A".equals(alphanumeric)) {
            return "14";
        }
        return alphanumeric;
    }


    public static void main(String[] args) {
        CardService cardService = new CardServiceImpl();
        List<String> cards = new ArrayList<>();
        cards.add("K@");
        cards.add("A@");
        cards.add("8@");
        cards.add("2@");
        cards.add("3@");
        cards.add("8*");
        System.out.println(cardService.alphanumericWinRule(cards));


        List<String> largestAndMostCards = new ArrayList<>();
        largestAndMostCards.add("A*");
        largestAndMostCards.add("A^");
        List<String> winnerCards = new ArrayList<>();
        winnerCards.add("A@");
        winnerCards.add("A*");
        int num = Integer.valueOf(cardService.alphaToNumeric(largestAndMostCards.get(0)));
        System.out.println(num);
        System.out.println(cardService.compareTo(largestAndMostCards, winnerCards));
    }
}
