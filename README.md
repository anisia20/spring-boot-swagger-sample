# Spring Boot 프로젝트 구조 (`demo` 프로젝트)

이 프로젝트는 **Spring Boot + JPA + PostgreSQL** 기반의 **RESTful API 서버**입니다.

---

## 프로젝트 구조 (`src/main/java/com/example/demo/`)

```
src/main/java/com/example/demo/  
│── common/          - 공통 유틸리티 및 상수 정의  
│   ├── constants/   - 프로젝트에서 사용하는 결과 코드 등 상수 관리  
│   │   ├── ResultCode  
│   ├── DemoUtil     - 공통 유틸리티 클래스  
│  
│── config/          - 설정 관련 클래스  
│   ├── ModelMapperConfig  - ModelMapper 설정  
│   ├── SwaggerConfig      - Swagger API 문서 설정  
│  
│── controller/      - API 컨트롤러 (엔드포인트 정의)  
│   ├── UserController     - 사용자 관련 API 컨트롤러  
│  
│── model/           - 도메인 모델 (DTO, 엔티티)  
│   ├── dto/         - DTO (데이터 전송 객체)  
│   │   ├── UserBaseDTO  
│   │   ├── UserCreateDTO  
│   │   ├── UserResponseDTO  
│   │   ├── UserUpdateDTO  
│   ├── entity/      - 엔티티 (DB 테이블 매핑)  
│   │   ├── User  
│   │   ├── Result  
│  
│── repository/      - 데이터 접근 레이어 (JPA Repository)  
│   ├── UserRepository  
│  
│── service/         - 비즈니스 로직 처리  
│   ├── UserService  
│  
│── DemoApplication  - Spring Boot 애플리케이션 시작점
```

---

## 주요 컴포넌트 설명

### common/ (공통 모듈)
- `constants/ResultCode` → API 응답 결과 코드 관리
- `DemoUtil` → 공통 유틸리티 함수 제공

### config/ (설정 파일)
- `ModelMapperConfig` → DTO ↔ Entity 변환을 위한 ModelMapper 설정
- `SwaggerConfig` → API 문서화를 위한 Swagger 설정

### controller/ (컨트롤러, REST API 엔드포인트)
- `UserController` → 사용자 CRUD API 엔드포인트 관리 (`/api/users`)

### model/ (데이터 모델)

#### dto/
- `UserBaseDTO` → 공통 속성을 가진 DTO
- `UserCreateDTO` → 사용자 생성 요청 DTO
- `UserResponseDTO` → 사용자 응답 DTO
- `UserUpdateDTO` → 사용자 업데이트 DTO

#### entity/
- `User` → 사용자 엔티티 (DB 매핑)
- `Result` → API 응답 포맷

### repository/ (데이터 액세스 계층)
- `UserRepository` → JPA 기반의 사용자 데이터 접근 레이어

### service/ (비즈니스 로직)
- `UserService` → `UserRepository`를 활용하여 사용자 CRUD 비즈니스 로직 수행

---

## 주요 기능
- 사용자 CRUD API (`UserController`)
- DTO ↔ Entity 변환 (`ModelMapper`)
- API 응답 표준화 (`Result`)
- Swagger 기반 API 문서 자동 생성
- JPA 기반 데이터베이스 연동 (`PostgreSQL`)

이 프로젝트는 `Spring Boot`의 Layered Architecture를 준수하며, 유지보수와 확장성을 고려한 구조로 설계되었습니다.
