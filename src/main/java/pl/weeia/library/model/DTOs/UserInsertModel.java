package pl.weeia.library.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UserInsertModel {
    private String name;
    @NotBlank
    private String password;
    @Email
    @NotEmpty
    private String email;

}
