FROM eclipse-temurin:21-jdk

# vi 설치
RUN apt-get update && apt-get install -y vim && rm -rf /var/lib/apt/lists/*

#로컬의 파일들이 컨테이너의 /app 경로 안으로 복사됩니다.
# 이후 명령어는 기본적으로 /app 디렉터리에서 실행됩니다.
WORKDIR /app
# 프로젝트 파일 전체 복사
COPY . .

# Gradle로 빌드 후 Jar 생성
RUN ./gradlew bootJar --no-daemon

# 빌드된 Jar 파일 실행
ENTRYPOINT ["java", "-jar", "build/libs/likelion-elasticsearch-0.0.1-SNAPSHOT.jar"]