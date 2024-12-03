package com.outsider.virtual.config;

import com.outsider.virtual.concert.command.application.dto.ConcertCreateDTO;
import com.outsider.virtual.concert.command.application.service.ConcertCreateService;
import com.outsider.virtual.concert.command.domain.aggregate.Concert;
import com.outsider.virtual.concert.command.domain.repository.ConcertRepository;
import com.outsider.virtual.song.command.application.dto.SongCreateDTO;
import com.outsider.virtual.song.command.application.service.SongCreateService;
import com.outsider.virtual.song.command.domain.aggregate.Song;
import com.outsider.virtual.song.command.domain.repository.SongRepository;
import com.outsider.virtual.stage.command.application.dto.StageCreateDTO;
import com.outsider.virtual.stage.command.application.service.StageCreateService;
import com.outsider.virtual.stage.command.domain.aggregate.Stage;
import com.outsider.virtual.stage.command.domain.repository.StageRepository;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class DummyConfig {

    private final ConcertCreateService concertService;
    private final StageCreateService stageService;
    private final RegistUserService userService;
    private final ConcertCollectionService collectionService;
    private final ViewHistoryService viewHistoryService;
    private final UserCommandRepository userRepository;
    private final StageRepository stageRepository;
    private final ConcertRepository concertRepository;
    private final SongCreateService songCreateService;
    private final SongRepository songRepository;

    @Autowired
    public DummyConfig(ConcertCreateService concertService, StageCreateService stageService,
                       RegistUserService userService, ConcertCollectionService collectionService,
                       ViewHistoryService viewHistoryService, UserCommandRepository userRepository,
                       StageRepository stageRepository, ConcertRepository concertRepository,
                       SongCreateService songCreateService, SongRepository songRepository) {
        this.concertService = concertService;
        this.stageService = stageService;
        this.userService = userService;
        this.collectionService = collectionService;
        this.viewHistoryService = viewHistoryService;
        this.userRepository = userRepository;
        this.stageRepository = stageRepository;
        this.concertRepository = concertRepository;
        this.songCreateService = songCreateService;
        this.songRepository = songRepository;
    }

   @PostConstruct
   public void init() {
       // UNNI, AQUA, 이세계 아이돌 아티스트와 준혁쨩 유저 생성
       Long artistId1 = createDummyUser("unni@example.com", "1", "UNNI","http://master-of-prediction.shop:9000/virtual-files/0190659f-8626-46a5-bcbf-b2f5b435e9d6.png");
       Long artistId2 = createDummyUser("aqua@example.com", "1", "AQUA","http://master-of-prediction.shop:9000/virtual-files/05cbab12-23b3-443b-bf7f-da45792124b3.png");
       Long artistId3 = createDummyUser("isekaiidol@example.com", "1", "이세계 아이돌","http://master-of-prediction.shop:9000/virtual-files/a6387c64-958e-4b05-837e-ff6ee0eb0c67.png");
       Long artistId4 = createDummyUser("arin@example.com", "1", "아린","http://master-of-prediction.shop:9000/virtual-files/d431da4f-deee-48cd-bd4e-8c23ee40db9f.png"); // 아린 추가
       Long audienceId = createDummyUser("junhyuk@example.com", "1", "준혁쨩","http://master-of-prediction.shop:9000/virtual-files/d431da4f-deee-48cd-bd4e-8c23ee40db9f.png");

       // Stage가 이미 존재하는지 확인 후 생성
       Long stageId = getOrCreateStage("Main Stage", 1, 1, 1, 1, "http://master-of-prediction.shop:9000/virtual-files/d431da4f-deee-48cd-bd4e-8c23ee40db9f.png");

       // 각 아티스트의 노래 생성
       List<Long> unniSongs = createSongs(artistId1, "UNNI의 노래", 3);
       List<Long> aquaSongs = createSongs(artistId2, "AQUA의 노래", 3);
       List<Long> isekaiSongs = createSongs(artistId3, "이세계 아이돌의 노래", 3);
       List<Long> arinSongs = createSongs(artistId4, "아린의 노래", 3);

       // Concert 더미 데이터 생성 (songIds 포함)
       Long concertId1 = getOrCreateConcert("유니 단독 콘서트 \"HELLO UNNI\"", "http://master-of-prediction.shop:9000/virtual-files/0190659f-8626-46a5-bcbf-b2f5b435e9d6.png",
               LocalDate.of(2024, 10, 31), LocalTime.of(18, 0), LocalTime.of(20, 30),
               1, 1, stageId, 1000, 500, unniSongs);

       Long concertId2 = getOrCreateConcert("아쿠아 단독 콘서트 \"I AM AQUA\"", "http://master-of-prediction.shop:9000/virtual-files/05cbab12-23b3-443b-bf7f-da45792124b3.png",
               LocalDate.of(2024, 11, 11), LocalTime.of(14, 0), LocalTime.of(15, 0),
               1, 1, stageId, 1500, 400, aquaSongs);

       Long concertId3 = getOrCreateConcert("이세계 아이돌 단독 콘서트 \"이세계 페스티벌\"", "http://master-of-prediction.shop:9000/virtual-files/a6387c64-958e-4b05-837e-ff6ee0eb0c67.png",
               LocalDate.of(2023, 9, 23), LocalTime.of(12, 0), LocalTime.of(21, 0),
               1, 1, stageId, 2000, 1000, isekaiSongs);

       Long concertId4 = getOrCreateConcert("아쿠아 단독 콘서트 \"PURPLELAR\"", "http://master-of-prediction.shop:9000/virtual-files/ba9ce80f-6b83-4786-8c90-16af229e813d.png",
               LocalDate.of(2024, 11, 14), LocalTime.of(11, 0), LocalTime.of(12, 0),
               1, 1, stageId, 1200, 300, arinSongs);
       // 아린 단독 콘서트 추가
       Long concertId5 = getOrCreateConcert("아린 단독 콘서트 \"STARLIGHT ARIN\"", "http://master-of-prediction.shop:9000/virtual-files/d431da4f-deee-48cd-bd4e-8c23ee40db9f.png",
               LocalDate.of(2024, 11, 28), LocalTime.of(19, 0), LocalTime.of(21, 30),
               2, 2, stageId, 2000, 800, createSongs(artistId4, "아린의 노래", 3));

       // 관람 이력 및 콜렉션 생성
       createDummyCollection(audienceId, concertId1);
       createDummyViewHistory(audienceId, concertId1);

       createDummyCollection(audienceId, concertId2);
       createDummyViewHistory(audienceId, concertId2);

       createDummyCollection(audienceId, concertId3);
       createDummyViewHistory(audienceId, concertId3);

       createDummyCollection(audienceId, concertId4);
       createDummyViewHistory(audienceId, concertId4);

       createDummyCollection(audienceId, concertId5); // 아린 콘서트 관람 이력 추가
       createDummyViewHistory(audienceId, concertId5); // 아린 콘서트 관람 기록 추가
   }

    private Long createDummyUser(String email, String password, String userName,String userImg) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return existingUser.get().getId();
        }
        SignUpRequestDTO dto = new SignUpRequestDTO();
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setUserName(userName);
        dto.setUserImg(userImg);
        return userService.registUser(dto);
    }

    private Long getOrCreateStage(String name, Integer terrain, Integer sky, Integer theme,
                                  Integer specialEffect, String img) {
        Optional<Stage> existingStage = stageRepository.findByName(name);
        if (existingStage.isPresent()) {
            return existingStage.get().getId();
        }
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

    private Long getOrCreateConcert(String name, String img, LocalDate concertDate, LocalTime startTime,
                                    LocalTime endTime, Integer appearedVFX, Integer feverVFX, Long stageId,
                                    Integer ticketPrice, Integer peopleScale, List<Long> songIds) {
        Optional<Concert> existingConcert = concertRepository.findByName(name);
        if (existingConcert.isPresent()) {
            return existingConcert.get().getId();
        }
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
        dto.setSongIds(songIds);

        Long userId = 1L;
        return concertService.register(userId, dto);
    }

    private void createDummyCollection(Long userId, Long concertId) {
        collectionService.collectConcert(userId, concertId);
    }

    private void createDummyViewHistory(Long userId, Long concertId) {
        viewHistoryService.createViewHistory(userId, concertId);
    }

    private List<Long> createSongs(Long artistId, String baseTitle, int count) {
        List<Long> songIds = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String title = baseTitle + " " + i;
            // 이미 존재하는 노래인지 확인
            Optional<Song> existingSong = songRepository.findByTitleAndArtistId(title, artistId);
            if (existingSong.isPresent()) {
                songIds.add(existingSong.get().getId());
                continue;
            }
            
            SongCreateDTO songDTO = new SongCreateDTO();
            songDTO.setTitle(title);
            songDTO.setUrl("http://example.com/songs/" + artistId + "/" + i + ".mp3");
            songDTO.setArtistId(artistId);
            songDTO.setDuration(180); // 3분
            Long songId = songCreateService.register(songDTO);
            songIds.add(songId);
        }
        return songIds;
    }
}
