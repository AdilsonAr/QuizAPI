package com.dev.dto;

import com.dev.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
	private String name;
	private String nickName;
	private String userPassword;
	private String email;
	
	public static User toModel(UserRequestDto u) {
		return new User(u.getName(), u.getNickName(), u.getUserPassword(), u.getEmail());
	}
}
