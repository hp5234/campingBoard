package newcamping.campingboard.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberViewDTO {

    /*
     * 출력용 회원 객체
     */

    private Long id;

    @NotEmpty
    private String loginId; // 로그인 ID
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;

}
