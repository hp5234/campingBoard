package newcamping.campingboard.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newcamping.campingboard.domain.member.MemberDTO;
import newcamping.campingboard.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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
    @PostMapping(value = "/check", consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, String>> checkMember(@RequestBody Map<String, Object> json){
        String loginId = (String) json.get("loginId");
        Long check = memberService.check(loginId);
        HashMap<String, String> map = new HashMap(); // response 용
        if (check == null ){
            map.put("result", "ok");
        } else {
            map.put("result", "fail");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
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
