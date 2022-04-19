package newcamping.campingboard.domain.member;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberDTO {

    /*
     * 회원 DTO
     */

    private Long id;

    @NotEmpty
    private String loginId; // 로그인 ID
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
}
