package com.dev.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.dev.model.Question;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResponseDto {
	private int questionId;
	private String question;
	private List<AnswerResponseDto> answers;
	public static QuestionResponseDto toDto(Question q) {
		List<AnswerResponseDto> answers = q.getAnswers().stream().map(x->{
			return AnswerResponseDto.toDto(x);
		}).collect(Collectors.toList());
		
		return new QuestionResponseDto(q.getId(), q.getQuestion(), answers);
	}
}
