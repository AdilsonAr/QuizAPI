package com.dev.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequestDto {
	@NotNull(message="testId is a required field")
	private int testId;
	@NotNull(message="questionId is a required field")
	private int questionId;
	@NotNull(message="answerId is a required field")
	private int answerId;
}
