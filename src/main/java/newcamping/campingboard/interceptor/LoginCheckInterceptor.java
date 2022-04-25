package newcamping.campingboard.interceptor;

import lombok.extern.slf4j.Slf4j;
import newcamping.campingboard.SessionConst;
import newcamping.campingboard.session.SessionDTO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession();
        SessionDTO sessionDTO = (SessionDTO) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if ( session == null || sessionDTO == null ) {
            log.info("미인증 사용자 요청 ");
            // 로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI); // 요청에 유저 id 포함
            return false;
        }
        log.info("session memberId = {}",sessionDTO.getId());
        request.setAttribute("ownerId", sessionDTO.getId());
        return true;
    }
}
