package com.dev.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestRequestDto {
	@NotBlank(message="categoryId is a required field")
	private int categoryId;
	@NotBlank(message="difficulty is a required field")
	private int difficulty;
}
