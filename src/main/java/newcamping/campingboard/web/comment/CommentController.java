package newcamping.campingboard.web.comment;

import lombok.RequiredArgsConstructor;
import newcamping.campingboard.domain.comment.CommentDTO;
import newcamping.campingboard.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    // 조회
    // notice controller 에서 게시물 조회와 함께 조회 실시

    // 추가
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/new")
    public String addComment(@ModelAttribute CommentForm commentForm) {
        Long commentId = commentService.save(commentForm);
        return "add comment success";
    }

    // 수정
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/edit")
    public String editComment(@ModelAttribute CommentDTO commentDTO) {
        int result = commentService.updateComment(commentDTO);
        return "edit comment success";
    }

    // 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    public String deleteComment(@RequestParam("commentId") Long commentId) {
        int result = commentService.deleteById(commentId);
        return "delete comment sucess";
    }
}
