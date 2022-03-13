package newcamping.campingboard.domain.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {

    private Long id;

    // 최종 수정 시간
    private LocalDateTime updatedTime;

    // 생성자_id
    private Long ownerId;

    // 내용
    private String contents;

    // 게시물_id
    private Long noticeId;

    // 좋아요
    private Long likeCount;

    // 싫어요
    private Long unlikeCount;

}
