package newcamping.campingboard.web.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeForm {
    // 게시판 id
    private Long boardId;

    // 생성자_id
    private Long ownerId;

    // 제목
    private String title;

    // 내용
    private String contents;
}
