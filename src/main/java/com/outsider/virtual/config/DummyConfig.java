package com.outsider.virtual.config;

import com.outsider.virtual.concert.command.application.dto.ConcertCreateDTO;
import com.outsider.virtual.concert.command.application.service.ConcertCreateService;
import com.outsider.virtual.stage.command.application.dto.StageCreateDTO;
import com.outsider.virtual.stage.command.application.service.StageCreateService;
import com.outsider.virtual.user.command.application.dto.SignUpRequestDTO;
import com.outsider.virtual.user.command.application.service.RegistUserService;
import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.repository.UserCommandRepository;
import com.outsider.virtual.useractivity.command.application.service.ConcertCollectionService;
import com.outsider.virtual.useractivity.command.application.service.ViewHistoryService;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Configuration
public class DummyConfig {

    private final ConcertCreateService concertService;
    private final StageCreateService stageService;
    private final RegistUserService userService;
    private final ConcertCollectionService collectionService;
    private final ViewHistoryService viewHistoryService;
    private final UserCommandRepository userRepository;

    @Autowired
    public DummyConfig(ConcertCreateService concertService, StageCreateService stageService,
                       RegistUserService userService, ConcertCollectionService collectionService,
                       ViewHistoryService viewHistoryService, UserCommandRepository userRepository) {
        this.concertService = concertService;
        this.stageService = stageService;
        this.userService = userService;
        this.collectionService = collectionService;
        this.viewHistoryService = viewHistoryService;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        // 사용자 더미 데이터 생성
        Long userId1 = createDummyUser("user1@example.com", "password1", "User1");
        Long userId2 = createDummyUser("user2@example.com", "password2", "User2");

        // Stage 더미 데이터 생성
        Long stageId1 = createDummyStage("Virtual Stage A", 1, 1, 1, 1, "http://example.com/stageA.jpg");
        Long stageId2 = createDummyStage("Virtual Stage B", 2, 1, 2, 2, "http://example.com/stageB.jpg");

        // Concert 더미 데이터 생성
        Long concertId1 = createDummyConcert("Virtual Concert A", "http://example.com/concertA.jpg",
                LocalDate.of(2024, 10, 14), LocalTime.of(18, 0), LocalTime.of(20, 0),
                1, 1, stageId1, 1000, 30);

        Long concertId2 = createDummyConcert("Virtual Concert B", "http://example.com/concertB.jpg",
                LocalDate.of(2024, 11, 15), LocalTime.of(19, 0), LocalTime.of(21, 0),
                2, 1, stageId2, 2000, 50);

        // 관람 이력 및 콜렉션 생성
        createDummyCollection(userId1, concertId1);
        createDummyCollection(userId2, concertId2);
        createDummyViewHistory(userId1, concertId1);
        createDummyViewHistory(userId2, concertId2);
    }

    private Long createDummyUser(String email, String password, String userName) {
        SignUpRequestDTO dto = new SignUpRequestDTO();
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setUserName(userName);

        Optional<User> existingUser = userRepository.findByEmail(email);
        return existingUser.map(User::getId).orElseGet(() -> userService.registUser(dto));
    }

    private Long createDummyStage(String name, Integer terrain, Integer sky, Integer theme,
                                  Integer specialEffect, String img) {
        StageCreateDTO dto = new StageCreateDTO();
        dto.setName(name);
        dto.setTerrain(terrain);
        dto.setSky(sky);
        dto.setTheme(theme);
        dto.setSpecialEffect(specialEffect);
        dto.setImg(img);

        Long userId = 1L;
        return stageService.register(userId, dto);
    }

    private Long createDummyConcert(String name, String img, LocalDate concertDate, LocalTime startTime,
                                    LocalTime endTime, Integer appearedVFX, Integer feverVFX, Long stageId,
                                    Integer ticketPrice, Integer peopleScale) {

        ConcertCreateDTO dto = new ConcertCreateDTO();
        dto.setName(name);
        dto.setImg(img);
        dto.setConcertDate(concertDate);
        dto.setStartTime(startTime);
        dto.setEndTime(endTime);
        dto.setAppearedVFX(appearedVFX);
        dto.setFeverVFX(feverVFX);
        dto.setStageId(stageId);
        dto.setTicketPrice(ticketPrice);
        dto.setPeopleScale(peopleScale);

        Long userId = 1L;
        return concertService.register(userId, dto);
    }

    private void createDummyCollection(Long userId, Long concertId) {
        collectionService.collectConcert(userId, concertId);
    }

    private void createDummyViewHistory(Long userId, Long concertId) {
        viewHistoryService.createViewHistory(userId, concertId);
    }
}
