package com.example.demo.service;

import com.example.demo.common.constants.ResultCode;
import com.example.demo.config.ModelMapperConfig;
import com.example.demo.model.Result;
import com.example.demo.model.dto.UserCreateDTO;
import com.example.demo.model.dto.UserResponseDTO;
import com.example.demo.model.dto.UserUpdateDTO;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.common.DemoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description    :
 * packageName    : com.example.demo.service
 * fileName       : UserService
 * author         : cho
 * date           : 2025. 3. 14.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 3. 14.        cho       최초 생성
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    private DemoUtil util;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperConfig modelMapper;

    public ResponseEntity<Result<List<UserResponseDTO>>> findAll() {
        Result<List<UserResponseDTO>> result = new Result<>();
        try {
            List<UserResponseDTO> users = userRepository.findAll().stream()
                    .map(user -> modelMapper.map(user, UserResponseDTO.class))
                    .collect(Collectors.toList());

            result.setSuccess(users);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            log.warn("User findAll failed. Error={}", e.getMessage());

            result.setResultFail(ResultCode.R_201);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    public ResponseEntity<Result<UserResponseDTO>> findById(long id) {
        Result<UserResponseDTO> result = new Result<>();
        if(util.isNullOrEmpty(id)){
            log.warn("Do not found id");
            return ResponseEntity.badRequest().body(result);
        }
        try {
            Optional<User> user = userRepository.findById(id);

            if (user.isPresent()) {
                UserResponseDTO userDTO = modelMapper.map(user.get(), UserResponseDTO.class);
                result.setSuccess(userDTO);
                return ResponseEntity.ok(result);
            } else {
                result.setResultFail(ResultCode.R_205);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(result);
            }
        } catch (DataAccessException e) {
            result.setResultFail(ResultCode.R_280);
            log.error("Database error while fetching user. ID={}, Error={}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(result);
        }
    }

    public ResponseEntity<Result<UserResponseDTO>> create(UserCreateDTO userCreateDTO) {
        // 1. DTO 유효성 검사
        Result<UserResponseDTO> result = util.isValid(userCreateDTO, new Result<>());
        if (!result.isEmpty()) {
            return ResponseEntity.badRequest().body(result);
        }

        // 2. DTO → Entity 변환
        User user = modelMapper.map(userCreateDTO, User.class);
        if(util.isNullOrEmpty(user)){
            log.warn("Do not match entity from dto, Email={}", userCreateDTO.getEmail());
            return ResponseEntity.badRequest().body(result);
        }

        try {
            // 3. 데이터 저장 및 ID 반환
            User savedUser = userRepository.save(user);

            // 4. 응답 데이터 구성
            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setId(savedUser.getId());
            responseDTO.setEmail(savedUser.getEmail());
            responseDTO.setName(savedUser.getName());

            result.setSuccess(responseDTO);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            log.warn("User creation failed. Email={}, Error={}", userCreateDTO.getEmail(), e.getMessage());

            result.setResultFail(ResultCode.R_280);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    public ResponseEntity<Result<UserResponseDTO>> update(UserUpdateDTO userDTO) {
        // 1. DTO 유효성 검사
        Result<UserResponseDTO> result = util.isValid(userDTO, new Result<>());
        if (!result.isEmpty()) {
            return ResponseEntity.badRequest().body(result);
        }

        try {
            // 2. 기존 User 찾기 (없으면 예외 발생)
            Optional<User> existingUser = userRepository.findById(userDTO.getId());
            if(!existingUser.isPresent()){
                log.warn("Do not match entity from dto, Email={}", userDTO.getEmail());
                result.setResultFail(ResultCode.R_205);
                return ResponseEntity.badRequest().body(result);
            }
            User user = existingUser.get();

            // 3. 기존 엔티티에 새로운 값 업데이트
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());

            User savedUser = userRepository.save(user);
            // 5. 응답 변환 및 반환
            UserResponseDTO responseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
            result.setSuccess(responseDTO);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            log.warn("Failed to update user. ID={}, Error={}", userDTO.getId(), e.getMessage());
            result.setResultFail(ResultCode.R_280);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    public ResponseEntity<Result> delete(Long id) {
        Result result = new Result();
        if(util.isNullOrEmpty(id)){
            log.warn("Do not found id");
            return ResponseEntity.badRequest().body(result);
        }

        try {
            boolean isFound = userRepository.existsById(id);
            if(!isFound){
                log.warn("Do not match entity from dto, Email={}", id);
                result.setResultFail(ResultCode.R_205);
                return ResponseEntity.badRequest().body(result);
            }
            userRepository.deleteById(id);
            result.setSuccess();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.warn("Failed to delete user. ID={}, Error={}", id, e.getMessage());
            result.setResultFail(ResultCode.R_280);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
