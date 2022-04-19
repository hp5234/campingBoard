package newcamping.campingboard.web.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {

    // 생성자_id
    private Long ownerId;

    // 내용
    private String contents;

    // 게시물_id
    private Long noticeId;
}
