package newcamping.campingboard.service;

import lombok.RequiredArgsConstructor;
import newcamping.campingboard.domain.member.MemberDTO;
import newcamping.campingboard.domain.member.MemberMapper;
import newcamping.campingboard.domain.member.MemberViewDTO;
import newcamping.campingboard.web.member.MemberForm;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    // loginId 중복검사
    public Long check(String loginId){
        return memberMapper.selectCheck(loginId);
    }

    // 회원가입 처리
    public Long save(MemberForm memberForm) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setLoginId(memberForm.getLoginId());
        memberDTO.setPassword(memberForm.getPassword());
        memberDTO.setName(memberForm.getName());
        memberDTO.setEmail(memberForm.getEmail());

        int result = memberMapper.insert(memberDTO);

        return memberDTO.getId();
    }

    // 출력용 회원 전체 조회
    public List<MemberViewDTO> findViewAll() {
        return memberMapper.selectViewAll();
    }

    // 출력용 회원 조회
    public MemberViewDTO findView(Long memberId){
        return memberMapper.selectViewById(memberId);
    }

    // 회원 단일 조회
    public MemberDTO findById(Long memberId){
        return memberMapper.selectById(memberId);
    }

    // 회원 수정 처리
    public int updateMember(MemberDTO memberDTO){
        return memberMapper.update(memberDTO);
    }

    // 회원 삭제 처리
    public int deleteById(Long memberId){
        return memberMapper.delete(memberId);
    }
}
