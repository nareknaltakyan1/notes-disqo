package com.notes.disqo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notes.disqo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    @Positive
    private Long id;

    @Size(max = 50)
    @NotBlank
    private String title;

    @Size(max = 1000)
    @NotBlank
    private String content;

    @JsonIgnore
    private UserDTO user;

//    @JsonIgnore
    private Date createDate;

//    @JsonIgnore
    private Date lastModifyDate;
}
