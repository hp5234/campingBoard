package newcamping.campingboard.filter;


import lombok.extern.slf4j.Slf4j;
import newcamping.campingboard.SessionConst;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    // 로그인 없이 이용할 수 있는 url
    private static final String[] whitelist = {"/", "/members/new", "/login", "/logout", "/css/*", "/js/*", "/notice/list"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LoginCheck filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);
            if ( isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    // 로그인으로 redirect
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI); // 로그인 후 현재 페이지로 넘어옴
                    return; // 미 인증 사용자는 이후를 진행하지 않고 끝낸다.
                    // 필터는 물론 서블릿, 컨트롤러가 더 호출되지 않고 redirect 가 응답으로 적용되고 요청이 끝난다.
                }
            }
            chain.doFilter(request, response);
        } catch ( Exception e) {
            throw e; // 예외 로깅은 가능하지만, 톰캣까지 예외를 보내주어야 함
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /**
     * 화이트 리스트의 경우 인증 체크 x
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !(PatternMatchUtils.simpleMatch(whitelist, requestURI));
    }

    @Override
    public void destroy() {
        log.info("LoginCheck filter destroy");
    }
}
