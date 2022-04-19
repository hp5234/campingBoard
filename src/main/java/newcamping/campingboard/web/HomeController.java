package newcamping.campingboard.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newcamping.campingboard.SessionConst;
import newcamping.campingboard.domain.board.BoardDTO;
import newcamping.campingboard.domain.member.MemberDTO;
import newcamping.campingboard.service.BoardService;
import newcamping.campingboard.service.MemberService;
import newcamping.campingboard.session.SessionDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final BoardService boardService;

    @GetMapping("/")
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) SessionDTO sessionDTO, Model model){

        List<BoardDTO> boards = boardService.findAll();
        model.addAttribute("boards", boards);

        // 로그인 실패
        // 세션에 회원 데이터가 없으면 home
        if( sessionDTO == null){
            return "home";
        }

        // 로그인 성공
        MemberDTO loginMember = memberService.findById(sessionDTO.getId());

        model.addAttribute("member", loginMember);
        return "home-login";
    }
}
