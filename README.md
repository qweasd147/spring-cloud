# Spring Cloud

## 사용법

```
$ git clone https://github.com/qweasd147/spring-cloud.git
$ ls ./spring-cloud
$ docker-compose up -d
```

### 1. 회원가입 요청

```sh
curl -X POST \
-F 'email=joohyung@gmail.com' \
-F 'name=joo' \
-F 'nickName=nickname421421' \
-F 'password=joo123' http://localhost:4000/auth/users/signup
```

#### 내부적으로 호출되는 서비스 순서

1. zuul(API Gateway)
2. cloud-auth(인증서버)

### 2. 토큰 생성 요청

```sh
$ curl -i -u clientId:secret \
-d "grant_type=password&username=(받은 idx 값)&password=joo123" \
-X POST http://localhost:4000/auth/oauth/token
```

#### 내부적으로 호출되는 서비스 순서

1. zuul(API Gateway)
2. cloud-auth(인증서버)

### 3. 서비스 a 호출 (인증 필요 없음)

```sh
curl -i 'http://localhost:4000/service/a'
```

#### 내부적으로 호출되는 서비스 순서

1. zuul(API Gateway)
2. service a
3. service b

### 4. 서비스 b 호출 (인증 필요)

```sh
curl 'http://localhost:4000/service/b' -H 'authorization: Bearer 발급받은 access token'

```

1. zuul(API Gateway)
2. service b

**참고**`API Gateway`에서 인증 절차(토큰 파싱)가 이루어진다

## 사용한 것들

1. Zuul (API Gateway)
2. Hystrix (Circuit Breaker)
3. Ribbon (L7 Load Balancing)
4. Config Server
5. 인증 서버(외부 docker image 활용)
