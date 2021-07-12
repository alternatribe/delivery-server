package net.endrigo.delivery.server.controller.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 128)
    private String name;
 
    @NotBlank
    @Size(min = 5, max = 128)
    @Email
    private String email;
    
    private String role;
    
    @NotBlank
    @Size(min = 6, max = 128)
    private String password;

}
