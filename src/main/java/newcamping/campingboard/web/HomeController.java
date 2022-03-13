package newcamping.campingboard.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    // 홈화면
    @GetMapping("/")
    public String home(Model model) {
        /*
            Board list 조회

            model 에
            1. Board list
            2. 로그인 여부
            3.
         */


        return "home";
    }
}
