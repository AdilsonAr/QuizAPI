package com.dev.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestRequestDto {
	@NotNull(message="categoryId is a required field")
	private int categoryId;
	@NotNull(message="difficulty is a required field")
	private int difficulty;
}
