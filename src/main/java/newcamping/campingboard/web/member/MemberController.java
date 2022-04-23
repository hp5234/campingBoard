package newcamping.campingboard.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newcamping.campingboard.domain.member.MemberDTO;
import newcamping.campingboard.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 회원가입 폼 호출
    @GetMapping("/new")
    public String addMemberForm() {
        return "member/addMemberForm";
    }

    // 회원 가입 처리
    @PostMapping("/new")
    public String addMember(@ModelAttribute("member") MemberForm memberForm, BindingResult bindingResult) {
        /*
            중복검사 실시
         */
        Long result = memberService.save(memberForm);
        return "redirect:/";
    }

    // 아이디 중복검사
    @PostMapping("/check")
    @ResponseBody
    public String checkMember(@RequestBody String loginId){
        log.info("request login={}", loginId);
        Long check = memberService.check(loginId);
        if (check != null ){
            return "fail";
        } else {
            return "ok";
        }
    }

    // 회원 수정 폼 호출
    @GetMapping("/edit/{memberId}")
    public ModelAndView editMemberForm( @PathVariable("memberId") Long memberId){
        ModelAndView mv = new ModelAndView();
        MemberDTO member = memberService.findById(memberId);
        mv.setViewName("member/editMemberForm");
        mv.addObject("member", member);

        return mv;
    }

    // 회원 수정 처리
    @PatchMapping("/edit")
    public String editMember( @ModelAttribute MemberDTO memberDTO) {
        memberService.updateMember(memberDTO);
        return "redirect:/";
    }

    // 회원 삭제 처리
    @DeleteMapping("/delete")
    public String deleteMember(@RequestParam("memberId") Long memberId){
        memberService.deleteById(memberId);
        return "redirect:/";
    }
}
