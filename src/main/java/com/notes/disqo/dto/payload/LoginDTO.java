package com.notes.disqo.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class LoginDTO {

    @NotEmpty
    @Size(min = 6, max = 50)
    @Email
    private String username;

    @NotEmpty
    @Size(min = 8, max = 50)
    private String password;
}
