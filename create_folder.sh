#!/bin/bash

# 기본 골조만 생성할지 여부 확인
SKELETON_ONLY=false
if [[ "$1" == "--skeleton-only" ]]; then
    SKELETON_ONLY=true
fi

# 베이스 디렉토리 설정
BASE_DIR="concert"  # 원하는 베이스 디렉토리로 변경하세요.
PACKAGE_NAME="com.outsider.virtual.${BASE_DIR}"  # BASE_DIR을 포함한 패키지 이름 생성
PACKAGE_PATH="src/main/java/${PACKAGE_NAME//./'/'}"  # src/main/java 경로 포함

# DOMAIN_NAME은 BASE_DIR을 PascalCase로 변환한 값입니다.
DOMAIN_NAME=$(echo "$BASE_DIR" | sed -r 's/(^|_)([a-z])/\U\2/g')
DOMAIN_NAME_LOWERCASE=$(echo "$DOMAIN_NAME" | tr '[:upper:]' '[:lower:]')  # DOMAIN_NAME을 소문자로 변환

# 기본 API 버전 설정
API_BASE_PATH="/api/v1"  # 기본 API 경로를 설정합니다.

# 어플리케이션 및 도메인 구조 생성
mkdir -p "$PACKAGE_PATH/command/application/service"
mkdir -p "$PACKAGE_PATH/command/application/controller"
mkdir -p "$PACKAGE_PATH/command/application/dto"
mkdir -p "$PACKAGE_PATH/command/domain/aggregate"
mkdir -p "$PACKAGE_PATH/command/domain/repository"
mkdir -p "$PACKAGE_PATH/command/domain/service"
mkdir -p "$PACKAGE_PATH/command/infrastructure"

mkdir -p "$PACKAGE_PATH/query/application/service"
mkdir -p "$PACKAGE_PATH/query/application/controller"
mkdir -p "$PACKAGE_PATH/query/application/dto"
mkdir -p "$PACKAGE_PATH/query/domain/repository"

echo "폴더 구조 생성이 완료되었습니다."

# Aggregate Entity 파일 생성
cat <<EOF > "$PACKAGE_PATH/command/domain/aggregate/${DOMAIN_NAME}.java"
package ${PACKAGE_NAME}.command.domain.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ${DOMAIN_NAME} {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Constructors
    public ${DOMAIN_NAME}() {}

    public ${DOMAIN_NAME}(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {  // setId 메서드 추가
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
EOF


# Repository Interface 파일 생성 (Command 용)
cat <<EOF > "$PACKAGE_PATH/command/domain/repository/${DOMAIN_NAME}Repository.java"
package ${PACKAGE_NAME}.command.domain.repository;

import ${PACKAGE_NAME}.command.domain.aggregate.${DOMAIN_NAME};
import org.springframework.data.jpa.repository.JpaRepository;

public interface ${DOMAIN_NAME}Repository extends JpaRepository<${DOMAIN_NAME}, Long> {
}
EOF

# DTO 파일 생성 (Create, Update, Delete용)
cat <<EOF > "$PACKAGE_PATH/command/application/dto/${DOMAIN_NAME}CreateDTO.java"
package ${PACKAGE_NAME}.command.application.dto;

public class ${DOMAIN_NAME}CreateDTO {
    private String name;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
EOF

cat <<EOF > "$PACKAGE_PATH/command/application/dto/${DOMAIN_NAME}UpdateDTO.java"
package ${PACKAGE_NAME}.command.application.dto;

public class ${DOMAIN_NAME}UpdateDTO {
    private Long id;
    private String name;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
EOF

cat <<EOF > "$PACKAGE_PATH/command/application/dto/${DOMAIN_NAME}DeleteDTO.java"
package ${PACKAGE_NAME}.command.application.dto;

public class ${DOMAIN_NAME}DeleteDTO {
    private Long id;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
EOF

# Create Service 파일 생성 (Entity <-> DTO 변환 함수 포함)
cat <<EOF > "$PACKAGE_PATH/command/application/service/${DOMAIN_NAME}CreateService.java"
package ${PACKAGE_NAME}.command.application.service;

import ${PACKAGE_NAME}.command.domain.aggregate.${DOMAIN_NAME};
import ${PACKAGE_NAME}.command.domain.repository.${DOMAIN_NAME}Repository;
import ${PACKAGE_NAME}.command.application.dto.${DOMAIN_NAME}CreateDTO;
import org.springframework.stereotype.Service;

@Service
public class ${DOMAIN_NAME}CreateService {

    private final ${DOMAIN_NAME}Repository ${DOMAIN_NAME_LOWERCASE}Repository;

    public ${DOMAIN_NAME}CreateService(${DOMAIN_NAME}Repository ${DOMAIN_NAME_LOWERCASE}Repository) {
        this.${DOMAIN_NAME_LOWERCASE}Repository = ${DOMAIN_NAME_LOWERCASE}Repository;
    }

    public void register(${DOMAIN_NAME}CreateDTO dto) {
        ${DOMAIN_NAME} entity = convertToEntity(dto);
        ${DOMAIN_NAME_LOWERCASE}Repository.save(entity);
    }


    // DTO -> Entity 변환
    public ${DOMAIN_NAME} convertToEntity(${DOMAIN_NAME}CreateDTO dto) {
        return new ${DOMAIN_NAME}(dto.getName());
    }
}
EOF

# Update Service 파일 생성 (Entity <-> DTO 변환 함수 포함)
cat <<EOF > "$PACKAGE_PATH/command/application/service/${DOMAIN_NAME}UpdateService.java"
package ${PACKAGE_NAME}.command.application.service;

import ${PACKAGE_NAME}.command.domain.aggregate.${DOMAIN_NAME};
import ${PACKAGE_NAME}.command.domain.repository.${DOMAIN_NAME}Repository;
import ${PACKAGE_NAME}.command.application.dto.${DOMAIN_NAME}UpdateDTO;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ${DOMAIN_NAME}UpdateService {

    private final ${DOMAIN_NAME}Repository ${DOMAIN_NAME_LOWERCASE}Repository;

    public ${DOMAIN_NAME}UpdateService(${DOMAIN_NAME}Repository ${DOMAIN_NAME_LOWERCASE}Repository) {
        this.${DOMAIN_NAME_LOWERCASE}Repository = ${DOMAIN_NAME_LOWERCASE}Repository;
    }

    public void update(${DOMAIN_NAME}UpdateDTO dto) {
        Optional<${DOMAIN_NAME}> optionalEntity = ${DOMAIN_NAME_LOWERCASE}Repository.findById(dto.getId());
        if (optionalEntity.isPresent()) {
            ${DOMAIN_NAME} entity = optionalEntity.get();
            entity.setName(dto.getName());
            ${DOMAIN_NAME_LOWERCASE}Repository.save(entity);
        } else {
            // 예외 처리 (엔티티를 찾을 수 없는 경우)
            throw new RuntimeException("${DOMAIN_NAME} not found");
        }
    }



    // DTO -> Entity 변환 (필요 시)
    public ${DOMAIN_NAME} convertToEntity(${DOMAIN_NAME}UpdateDTO dto) {
        ${DOMAIN_NAME} entity = new ${DOMAIN_NAME}();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
EOF

# Delete Service 파일 생성
cat <<EOF > "$PACKAGE_PATH/command/application/service/${DOMAIN_NAME}DeleteService.java"
package ${PACKAGE_NAME}.command.application.service;

import ${PACKAGE_NAME}.command.domain.repository.${DOMAIN_NAME}Repository;
import ${PACKAGE_NAME}.command.application.dto.${DOMAIN_NAME}DeleteDTO;
import org.springframework.stereotype.Service;

@Service
public class ${DOMAIN_NAME}DeleteService {

    private final ${DOMAIN_NAME}Repository ${DOMAIN_NAME_LOWERCASE}Repository;

    public ${DOMAIN_NAME}DeleteService(${DOMAIN_NAME}Repository ${DOMAIN_NAME_LOWERCASE}Repository) {
        this.${DOMAIN_NAME_LOWERCASE}Repository = ${DOMAIN_NAME_LOWERCASE}Repository;
    }

    public void delete(${DOMAIN_NAME}DeleteDTO dto) {
        ${DOMAIN_NAME_LOWERCASE}Repository.deleteById(dto.getId());
    }
}
EOF

# Command Controller 파일 생성 (DTO 사용)
cat <<EOF > "$PACKAGE_PATH/command/application/controller/${DOMAIN_NAME}CommandController.java"
package ${PACKAGE_NAME}.command.application.controller;

import ${PACKAGE_NAME}.command.application.service.${DOMAIN_NAME}CreateService;
import ${PACKAGE_NAME}.command.application.service.${DOMAIN_NAME}UpdateService;
import ${PACKAGE_NAME}.command.application.service.${DOMAIN_NAME}DeleteService;
import ${PACKAGE_NAME}.command.application.dto.${DOMAIN_NAME}CreateDTO;
import ${PACKAGE_NAME}.command.application.dto.${DOMAIN_NAME}UpdateDTO;
import ${PACKAGE_NAME}.command.application.dto.${DOMAIN_NAME}DeleteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${API_BASE_PATH}/${DOMAIN_NAME_LOWERCASE}s")
public class ${DOMAIN_NAME}CommandController {

    private final ${DOMAIN_NAME}CreateService ${DOMAIN_NAME_LOWERCASE}CreateService;
    private final ${DOMAIN_NAME}UpdateService ${DOMAIN_NAME_LOWERCASE}UpdateService;
    private final ${DOMAIN_NAME}DeleteService ${DOMAIN_NAME_LOWERCASE}DeleteService;

    public ${DOMAIN_NAME}CommandController(
            ${DOMAIN_NAME}CreateService ${DOMAIN_NAME_LOWERCASE}CreateService,
            ${DOMAIN_NAME}UpdateService ${DOMAIN_NAME_LOWERCASE}UpdateService,
            ${DOMAIN_NAME}DeleteService ${DOMAIN_NAME_LOWERCASE}DeleteService) {
        this.${DOMAIN_NAME_LOWERCASE}CreateService = ${DOMAIN_NAME_LOWERCASE}CreateService;
        this.${DOMAIN_NAME_LOWERCASE}UpdateService = ${DOMAIN_NAME_LOWERCASE}UpdateService;
        this.${DOMAIN_NAME_LOWERCASE}DeleteService = ${DOMAIN_NAME_LOWERCASE}DeleteService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody ${DOMAIN_NAME}CreateDTO dto) {
        ${DOMAIN_NAME_LOWERCASE}CreateService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("성공적으로 생성되었습니다.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Long id,
            @RequestBody ${DOMAIN_NAME}UpdateDTO dto) {
        dto.setId(id);
         ${DOMAIN_NAME_LOWERCASE}UpdateService.update(dto);
        return ResponseEntity.ok("성공적으로 업데이트되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ${DOMAIN_NAME}DeleteDTO dto = new ${DOMAIN_NAME}DeleteDTO();
        dto.setId(id);
        ${DOMAIN_NAME_LOWERCASE}DeleteService.delete(dto);
        return ResponseEntity.noContent().build();
    }
}
EOF

# Query 서비스용 Repository 생성
cat <<EOF > "$PACKAGE_PATH/query/domain/repository/${DOMAIN_NAME}QueryRepository.java"
package ${PACKAGE_NAME}.query.domain.repository;

import ${PACKAGE_NAME}.command.domain.aggregate.${DOMAIN_NAME};
import org.springframework.data.jpa.repository.JpaRepository;

public interface ${DOMAIN_NAME}QueryRepository extends JpaRepository<${DOMAIN_NAME}, Long> {
}
EOF

# Query DTO 파일 생성
cat <<EOF > "$PACKAGE_PATH/query/application/dto/${DOMAIN_NAME}DTO.java"
package ${PACKAGE_NAME}.query.application.dto;

public class ${DOMAIN_NAME}DTO {
    private Long id;
    private String name;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
EOF

# Query Service 파일 생성 (Entity <-> DTO 변환 함수 포함)
cat <<EOF > "$PACKAGE_PATH/query/application/service/${DOMAIN_NAME}QueryService.java"
package ${PACKAGE_NAME}.query.application.service;

import ${PACKAGE_NAME}.command.domain.aggregate.${DOMAIN_NAME};
import ${PACKAGE_NAME}.query.domain.repository.${DOMAIN_NAME}QueryRepository;
import ${PACKAGE_NAME}.query.application.dto.${DOMAIN_NAME}DTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ${DOMAIN_NAME}QueryService {

    private final ${DOMAIN_NAME}QueryRepository ${DOMAIN_NAME_LOWERCASE}QueryRepository;

    public ${DOMAIN_NAME}QueryService(${DOMAIN_NAME}QueryRepository ${DOMAIN_NAME_LOWERCASE}QueryRepository) {
        this.${DOMAIN_NAME_LOWERCASE}QueryRepository = ${DOMAIN_NAME_LOWERCASE}QueryRepository;
    }

    public List<${DOMAIN_NAME}DTO> getAll${DOMAIN_NAME}s() {
        List<${DOMAIN_NAME}> entities = ${DOMAIN_NAME_LOWERCASE}QueryRepository.findAll();
        return entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ${DOMAIN_NAME}DTO get${DOMAIN_NAME}ById(Long id) {
        ${DOMAIN_NAME} entity = ${DOMAIN_NAME_LOWERCASE}QueryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("${DOMAIN_NAME} not found"));
        return convertToDTO(entity);
    }

    // Entity -> DTO 변환
    public ${DOMAIN_NAME}DTO convertToDTO(${DOMAIN_NAME} entity) {
        ${DOMAIN_NAME}DTO dto = new ${DOMAIN_NAME}DTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
EOF

# Query Controller 파일 생성
cat <<EOF > "$PACKAGE_PATH/query/application/controller/${DOMAIN_NAME}QueryController.java"
package ${PACKAGE_NAME}.query.application.controller;

import ${PACKAGE_NAME}.query.application.service.${DOMAIN_NAME}QueryService;
import ${PACKAGE_NAME}.query.application.dto.${DOMAIN_NAME}DTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${API_BASE_PATH}/${DOMAIN_NAME_LOWERCASE}s")
public class ${DOMAIN_NAME}QueryController {

    private final ${DOMAIN_NAME}QueryService ${DOMAIN_NAME_LOWERCASE}QueryService;

    public ${DOMAIN_NAME}QueryController(${DOMAIN_NAME}QueryService ${DOMAIN_NAME_LOWERCASE}QueryService) {
        this.${DOMAIN_NAME_LOWERCASE}QueryService = ${DOMAIN_NAME_LOWERCASE}QueryService;
    }

    @GetMapping
    public List<${DOMAIN_NAME}DTO> getAll${DOMAIN_NAME}s() {
        return ${DOMAIN_NAME_LOWERCASE}QueryService.getAll${DOMAIN_NAME}s();
    }

    @GetMapping("/{id}")
    public ${DOMAIN_NAME}DTO get${DOMAIN_NAME}ById(@PathVariable Long id) {
        return ${DOMAIN_NAME_LOWERCASE}QueryService.get${DOMAIN_NAME}ById(id);
    }
}
EOF

echo "기본 파일 및 폴더 구조 생성이 완료되었습니다."
