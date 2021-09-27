package com.dev.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.LabelDto;
import com.dev.dto.ResponseDto;
import com.dev.model.Difficulties;

@RestController
@RequestMapping("/difficulties")
public class DifficultyController {
	@GetMapping
	public ResponseEntity<?> readAll() {

		List<LabelDto> labels = (Arrays.asList(Difficulties.values())).stream().map(x -> {
			return new LabelDto(x.getId(), x.getLabel());
		}).collect(Collectors.toList());
		
		ResponseDto<List<LabelDto>> diff = new ResponseDto<>(labels);
		return new ResponseEntity<>(diff, HttpStatus.OK);
	}
}
