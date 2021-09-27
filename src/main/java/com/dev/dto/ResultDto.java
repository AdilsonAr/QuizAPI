package com.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
	private int testId;
	private String difficultyLevel;
	private int userId;
	private double grade;
	private String category;
	private String subCategory;
}
