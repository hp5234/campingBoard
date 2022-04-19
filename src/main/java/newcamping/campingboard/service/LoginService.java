package newcamping.campingboard.service;

import lombok.RequiredArgsConstructor;
import newcamping.campingboard.domain.member.MemberDTO;
import newcamping.campingboard.domain.member.MemberMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberMapper memberMapper;

    /**
     * 로그인 처리
     * return null 이면 로그인 실패
     */
    public MemberDTO login(String loginId, String password) {

        // 암호화 미적용
        return memberMapper.selectByLoginId(loginId).filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}