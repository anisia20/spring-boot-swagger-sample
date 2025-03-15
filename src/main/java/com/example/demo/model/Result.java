package com.example.demo.model;

import com.example.demo.common.constants.ResultCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * description    :
 * packageName    : com.example.demo.model.dto
 * fileName       : Result
 * author         : cho
 * date           : 2025. 3. 15.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 3. 15.        cho       최초 생성
 */
@Getter
@Setter
public class Result<T> {

    private String result;             // 결과 코드
    private String resultDescription;  // 결과 메시지
    private T data;                    // 응답 데이터

    // 기본 생성자
    public Result() {}

    // 성공 응답 생성
    public Result(ResultCode resultCode) {
        this.result = resultCode.r;
        this.resultDescription = resultCode.rd;
    }

    public Result(ResultCode resultCode, T data) {
        this.result = resultCode.r;
        this.resultDescription = resultCode.rd;
        this.data = data;
    }

    // 성공 응답 설정
    @JsonIgnore // JSON 응답에서 제외
    @Schema(hidden = true) // Swagger 문서에서도 제외
    public void setSuccess() {
        this.result = ResultCode.R_000.r;
        this.resultDescription = ResultCode.R_000.rd;
    }

    @JsonIgnore // JSON 응답에서 제외
    @Schema(hidden = true) // Swagger 문서에서도 제외
    public void setSuccess(T data) {
        this.result = ResultCode.R_000.r;
        this.resultDescription = ResultCode.R_000.rd;
        this.data = data;
    }

    // 실패 응답 설정
    public void setResultFail(ResultCode resultCode) {
        this.result = resultCode.r;
        this.resultDescription = resultCode.rd;
    }

    public void setResultFail(ResultCode resultCode, T data) {
        this.result = resultCode.r;
        this.resultDescription = resultCode.rd;
        this.data = data;
    }

    @JsonIgnore
    @Schema(hidden = true)
    public boolean isEmpty() {
        return this.result == null;
    }
}
