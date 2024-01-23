package com.sw.test.controller;

import com.sw.test.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardPlayRoomController {



    /**
     * Th RESTful API of get all available rooms.
     * @return
     */
    @CrossOrigin
    @GetMapping(path = "/room/list")
    @ResponseBody
    public ResponseEntity<ResponseVO<List<String>>> findAll() {
        return new ResponseEntity<ResponseVO<List<String>>>(
                new ResponseVO<>(100,
                        "success",
                        null
                ),
                HttpStatus.OK
        );
    }

}