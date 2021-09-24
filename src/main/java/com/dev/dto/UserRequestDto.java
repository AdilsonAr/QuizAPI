package com.dev.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

import com.dev.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
	@NotBlank(message="name is required")
	private String name;
	@NotBlank(message="nickName is required")
	private String nickName;
	@NotBlank(message="userPassword is required")
	private String userPassword;
	@NotBlank(message="email is required")
	@Email(message="the email field does not seem to be a valid e-mail")
	private String email;
	
	public static User toModel(UserRequestDto u) {
		return new User(u.getName(), u.getNickName(), u.getUserPassword(), u.getEmail());
	}
}
