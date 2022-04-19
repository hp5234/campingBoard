package newcamping.campingboard.domain.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BoardDTO {

    /**
     * 게시판 DTO
     */

    private Long id;

    // 생성 시간
    private LocalDateTime createTime;

    // 최종 수정 시간
    private LocalDateTime updateTime;

    // 위도
    private String latitude;

    // 경도
    private String longitude;

    // 생성자_id
    private Long ownerId;

    // 제목
    private String title;

    // 내용
    private String contents;

    // 게시물 갯수
    private Long  noticeCount;
}