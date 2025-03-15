package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * description    :
 * packageName    : com.example.demo.model.dto
 * fileName       : UserCreateDTO
 * author         : cho
 * date           : 2025. 3. 14.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 3. 14.        cho       최초 생성
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBaceDTO {
    @NotNull(message="202")
    @NotEmpty(message = "202")
    private String email;

    @NotNull(message="203")
    @NotEmpty(message = "203")
    private String name;
}
