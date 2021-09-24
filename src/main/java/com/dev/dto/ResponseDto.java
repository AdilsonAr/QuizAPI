package com.dev.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto <T>{
	private T data;
	private List<String> errors;
	
	public void addError(String error) {
		if(errors==null) {
			errors = new ArrayList<>();
		}
		errors.add(error);
	}
	
	public ResponseDto(T data) {
		super();
		this.data = data;
		errors=Arrays.asList("");
	}

	public ResponseDto() {
		super();
		data=(T) "";
	}

}