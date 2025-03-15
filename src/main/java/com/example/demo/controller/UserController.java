package com.example.demo.controller;

import com.example.demo.model.Result;
import com.example.demo.model.dto.UserCreateDTO;
import com.example.demo.model.dto.UserResponseDTO;
import com.example.demo.model.dto.UserUpdateDTO;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description    :
 * packageName    : com.example.demo.controller
 * fileName       : UserController
 * author         : cho
 * date           : 2025. 3. 14.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 3. 14.        cho       최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(summary = "사용자 조회", description = "사용자를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = UserCreateDTO.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    @GetMapping
    public ResponseEntity<Result<List<UserResponseDTO>>> loadAll() {
        return userService.findAll();
    }

    @Operation(summary = "사용자 조회", description = "ID로 특정 사용자를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "id 에러"),
            @ApiResponse(responseCode = "500", description = "DB 에러")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Result<UserResponseDTO>> loadOne(@PathVariable long id) {
        return userService.findById(id);
    }

    @Operation(summary = "사용자 생성", description = "사용자를 생성합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "DTO 에러"),
            @ApiResponse(responseCode = "500", description = "DB 에러")
    })
    @PostMapping
    public ResponseEntity<Result<UserResponseDTO>> create(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.create(userCreateDTO);
    }

    @Operation(summary = "사용자 수정", description = "사용자를 수정합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "DTO 에러"),
            @ApiResponse(responseCode = "500", description = "DB 에러")
    })
    @PutMapping
    public ResponseEntity<Result<UserResponseDTO>> update(@RequestBody UserUpdateDTO userDTO) {
            return userService.update(userDTO);
    }

    @Operation(summary = "사용자 삭제", description = "사용자를 삭제합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "DTO 에러"),
            @ApiResponse(responseCode = "500", description = "DB 에러")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
