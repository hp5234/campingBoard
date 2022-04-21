package newcamping.campingboard.web.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newcamping.campingboard.domain.comment.CommentDTO;
import newcamping.campingboard.domain.notice.NoticeDTO;
import newcamping.campingboard.service.CommentService;
import newcamping.campingboard.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final CommentService commentService;

    // 게시물 추가 페이지 호출
    @PostMapping("/new/form")
    public ModelAndView addNoticeForm(@RequestParam("boardId") Long boardId, @RequestParam("ownerId") Long ownerId, BindingResult bindingResult ) {

        ModelAndView mv = new ModelAndView();

        if (bindingResult.hasErrors() ) {
            mv.setViewName("redirect:/");
            return mv;
        }

        else {
            mv.setViewName("notice/addNoticeForm");
            mv.addObject("boardId", boardId);
            mv.addObject("ownerId", ownerId);
        }

        return mv;
    }

    // 게시물 추가 처리
    @PostMapping("/new")
    public String addNotice( @ModelAttribute NoticeForm noticeForm) {
        noticeService.save(noticeForm);
        return "redirect:/";
    }

    // 게시물 전체 조회 - 게시판 id 를 통한 조회
    @GetMapping("/list/{boardId}")
    @ResponseBody
    public List<NoticeDTO> findAll (@PathVariable("boardId") String boardIdString) {
        log.info("boardId = {}", boardIdString);
        Long boardId = Long.parseLong(boardIdString);
        return noticeService.findAll(boardId);
    }


    // 게시물 조회 - 상세 페이지
    @GetMapping("/{id}")
    public ModelAndView viewNotice( @PathVariable("id") Long noticeId){
        ModelAndView mv = new ModelAndView();
        NoticeDTO notice = noticeService.findById(noticeId);
        List<CommentDTO> comments = commentService.findAllByNoticeId(noticeId);
        mv.addObject("notice", notice);
        mv.addObject("comments", comments);
        mv.setViewName("notice/viewNotice");

        return mv;
    }

    // 게시물 수정 페이지 호출
    @GetMapping("/edit/{noticeId}")
    public ModelAndView editNoticeForm( @PathVariable("noticeId") Long noticeId) {
        ModelAndView mv = new ModelAndView();
        NoticeDTO notice = noticeService.findById(noticeId);
        mv.setViewName("notice/editNoticeForm");
        mv.addObject("notice",notice);

        return mv;
    }

    // 게시물 수정
    @PatchMapping("/edit")
    public String editNotice( @ModelAttribute NoticeDTO noticeDTO){
        noticeService.updateNotice(noticeDTO);
        return "redirect:/";
    }

    // 게시물 삭제 처리
    @DeleteMapping("/delete")
    public String deleteNotice( @RequestParam("noticeId") Long noticeId) {
        noticeService.deleteById(noticeId);
        return "redirect:/";
    }
}
