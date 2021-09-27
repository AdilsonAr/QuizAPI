package com.dev.dto;

import com.dev.model.Answer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerResponseDto {
	private int answerId;
	private String answer;
	public static AnswerResponseDto toDto(Answer a) {
		return new AnswerResponseDto(a.getId(), a.getAnswer());
	}
}
