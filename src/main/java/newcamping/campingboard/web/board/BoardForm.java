package newcamping.campingboard.web.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm {

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

}
