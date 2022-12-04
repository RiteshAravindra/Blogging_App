package com.coderitesh.bloogingapp.payloads;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@Size(min=4,message="username must be min of four characters")
	private String name;
	
	@NotEmpty
	@Email(message="your given email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=7,message="Password must be minimum of seven length")
//	@Pattern()
	private String password;
	
	@NotEmpty
	private String about;
	
	
	
}
