package newcamping.campingboard.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue
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
