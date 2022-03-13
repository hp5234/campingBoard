package newcamping.campingboard.domain.notice;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notice {

    private Long id;

    // 생성 시간
    private LocalDateTime createdTime;

    // 최종 수정 시간
    private LocalDateTime updatedTime;

    // 생성자_id
    private Long ownerId;

    // 제목
    private String title;

    // 내용
    private String contents;

    // 댓글 수
    private Long commentCount;

    // 조회 수
    private Long  viewCount;

    // 좋아요
    private Long likeCount;

    // 싫어요
    private Long unlikeCount;


}


