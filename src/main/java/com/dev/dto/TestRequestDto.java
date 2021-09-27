package com.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestRequestDto {
	private int categoryId;
	private int subcategoryId;
	private int difficulty;
}
