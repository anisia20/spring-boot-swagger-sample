package com.example.demo.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;

/**
 * description    :
 * packageName    : com.example.demo.code
 * fileName       : ResultCode
 * author         : cho
 * date           : 2025. 3. 15.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 3. 15.        cho       최초 생성
 */
@Getter
@AllArgsConstructor
@ToString
public enum ResultCode {

    /** 내부 코드 체계 */
    R_000("000", "COMMON", "성공", "성공"),

    R_201("201", "USER", "사용자 등록 실패", "사용자 등록 실패"),
    R_202("202", "USER", "이메일 오류", "이메일 오류"),
    R_203("203", "USER", "이름 오류", "이름 오류"),
    R_204("204", "USER", "아이디 오류", "아이디 오류"),
    R_205("205", "USER", "사용자 없음", "사용자 없음"),

    R_280("280", "SYS", "데이터베이스 오류", "데이터베이스 오류"),

    R_ETC("999", "ETC", "기타", "기타오류"),
            ;

    public String r;
    public String tp;
    public String rd;
    public String cd;

    public static HashMap<String, ResultCode> getResultCode() {
        HashMap<String, ResultCode> codeMap = new HashMap<>();
        for (ResultCode c : values()) {
            codeMap.put(c.r, c);
        }
        return codeMap;
    }

    public static ResultCode getResultCd(String result) {
        return getResultCd(result, ResultCode.R_ETC);
    }

    public static ResultCode getResultCd(String result, ResultCode rcsresultCd) {
        ResultCode resultCd = null;
        try {
            resultCd = ResultCode.valueOf(result);
        } catch (Exception e) {
            resultCd = (rcsresultCd == null) ? ResultCode.R_ETC : rcsresultCd;
        }
        return resultCd;
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Prefix {
        PREFIX_R("R_"),
        ;
        public String key;
    }
}
