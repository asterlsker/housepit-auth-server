# Authorization Server

## Tech Stack

- Spring WebFlux
- Kotlin
- gRPC
- Redisson
- JPA
- [Kotlin JDSL](https://github.com/line/kotlin-jdsl/blob/main/reactive-core/README.md)
- Hibernate reactive
- Mutiny
- OAuth2
  - Google API Client
- Kotest
  - Describe Spec
  
## Auth Strategy

- Sliding Sessions
- JWT Token Payload
  - memberId
  - role
  - userName
  
## Package

- interfaces
  - GrpcService, RestController, DTO, Mapper
- application
  - Facade
- domain
  - Domain Class, Model, Domain Service 및 비지니스 업무 로직 담당 클래스
- infrastructure
  - Entity, Repository
  - 세부 구현 기술
  
> __Entity Class 와 Domain Class 를 분리한 이유__
>
> 인증 서비스의 경우에는 일단 한 번 만들어지고나서 비지니스의 요구사항이 자주 추가되거나 변경되지 않습니다. 또한 내부 서비스들로부터 많은 인증 요청을 수시로 받기 때문에 안정성이 고려되어야 하는 서비스입니다. 따라서 Auth Server 의 경우에는 생산성(productivity) 보다 안정성(Stability) 이 더 중요하다고 생각했습니다. DB 접근 기술이 바뀌더라도 Domain Layer 에 수정이 없도록 하기 위해 서로 다른 패키지에서 정의를 하였습니다.
  
## Naming Strategy

- Domain Layer 에서 사용되는 DTO 성 클래스들: xxxCommand, xxxQuery
- Service 라는 네이밍은 도메인 서비스에만 적용
- Application Layer 에서는 Facade 라는 네이밍 사용
- Spec
  - Ex. 토큰을 발급하기 위한 명세
  
> Domain Layer 에서 사용하는 DTO 성 클래스와, Infrastructure Layer 에서 사용되는 Repository 에 CQRS 를 적용하였습니다.
  
## Member Entity Spec

- PK: Auto-Increment
- Unique: UUID
  - Client 에서 사용하기 위함
