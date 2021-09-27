package com.dev.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.dev.model.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestResponseDto {
	private int quizId;
	private List<QuestionResponseDto> questions;
	public static TestResponseDto toDto(Test t) {
		List<QuestionResponseDto> questions = t.getItems().stream().map(x->{
			return QuestionResponseDto.toDto(x.getQuestion());
		}).collect(Collectors.toList());
		return new TestResponseDto(t.getId(), questions);
	}
}
