package com.dev.controller;

import com.dev.model.User;
import com.dev.service.SheetWriterService;
import com.dev.service.TestGradesViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/xls")
public class SpreedshetController {

    @Autowired
    private TestGradesViewService testGradesViewService;

    @GetMapping
    public ResponseEntity<?> xlsReport(@RequestParam int userId, @RequestParam String email) throws IOException {
        try {
            SheetWriterService sws = new SheetWriterService();
            sws.createReportByUserId(userId, email);
            return new ResponseEntity<>(sws, HttpStatus.OK);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
