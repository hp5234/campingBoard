package newcamping.campingboard.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newcamping.campingboard.SessionConst;
import newcamping.campingboard.domain.member.MemberDTO;
import newcamping.campingboard.session.SessionDTO;
import newcamping.campingboard.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 로그인 화면 출력
    @GetMapping("/login")
    public String loginForm() {
        return "login/loginForm";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult,
                          @RequestParam(defaultValue = "/") String redirectURL,
                          HttpServletRequest request ) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        MemberDTO loginedMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginedMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다. ");
            return "login/loginForm";
        }

        // 로그인 성공 처리
        // 세션 생성 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성 // 옵션 true false
        HttpSession session = request.getSession();

        // 세션에 저장할 객체 생성
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(loginedMember.getId());
        sessionDTO.setLoginId(loginedMember.getLoginId());

        // 세션에 로그인 회원 정보(세션 전용 객체) 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, sessionDTO);

        // 로그인 시 redirect 설정
        return "redirect:" + redirectURL;
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate(); // 세션 제거
        }

        return "redirect:/";
    }
}
