package newcamping.campingboard.web.board;

import lombok.RequiredArgsConstructor;
import newcamping.campingboard.domain.board.BoardDTO;
import newcamping.campingboard.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 게시판 추가 페이지 호출
    @PostMapping("/new/form")
    public ModelAndView addBoardForm(@RequestParam("ownerId") String ownerId,  @RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude, BindingResult bindingResult ) {

        ModelAndView mv = new ModelAndView();

        if (bindingResult.hasErrors() ) {
            mv.setViewName("redirect:/");
            return mv;
        }

        // 위도 경도 유효성 검사 -> 수정 예정
        if (latitude == null || longitude ==null) {
            bindingResult.reject("callAddBoardFormFail", "위도, 경도 값을 확인하세요. ");
            mv.setViewName("redirect:/");
            return mv;
        }

        // 등록된 장소가 존재하는지 조회
        int selectResult = boardService.findBySpace(latitude, longitude);

        if (selectResult != 0 ) {
            bindingResult.reject("used Space", "이미 생성된 장소입니다. ");
            mv.setViewName("redirect:/");
            return mv;
        }
        else {
            mv.setViewName("board/addBoardForm");
            mv.addObject("latitude", latitude);
            mv.addObject("longitude", longitude);
        }

        return mv;
    }

    // 게시판 추가 처리
    @PostMapping("/new")
    public String addBoard( @ModelAttribute BoardForm boardForm) {
        boardService.save(boardForm);
        return "redirect:/";
    }

    // 게시판 수정 폼 호출
    @GetMapping("/edit/{boardId}")
    public ModelAndView editBoardForm( @PathVariable("boardId") Long boardId){
        ModelAndView mv = new ModelAndView();
        BoardDTO board = boardService.findById(boardId);
        mv.setViewName("board/editBoardForm");
        mv.addObject("board",board);

        return mv;
    }

    // 게시판 수정 처리
    @PatchMapping("/edit")
    public String editBoard( @ModelAttribute BoardDTO boardDTO){
        boardService.updateBoard(boardDTO);
        return "redirect:/";
    }

    // 게시판 삭제 처리
    @DeleteMapping("/delete")
    public String deleteBoard( @RequestParam("boardId") Long boardId) {
        boardService.deleteById(boardId);
        return "redirect:/";
    }
}
