package com.harshit.blogapi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
 
	private Integer id;
	
	@NotEmpty
	@Size(min = 4, message="User name must be min 4 char")
	private String name;
	
	@Email(message="Invalid mail id")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=10, message="password mustbe greater than 3 and less than 10")
	private String password;
	
	@NotEmpty(message="about can't be empty")
	private String about;
}
