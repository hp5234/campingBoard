package newcamping.campingboard.web.comment;

import lombok.RequiredArgsConstructor;
import newcamping.campingboard.domain.comment.CommentDTO;
import newcamping.campingboard.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    // 조회
    // notice controller 에서 게시물 조회와 함께 조회 실시

    // 추가
    @PostMapping("/new")
    public ResponseEntity<Map<String, Long>> addComment(@ModelAttribute CommentForm commentForm) {
        Long commentId = commentService.save(commentForm);
        Map<String, Long> map = new HashMap<>();
        map.put("commentId", commentId);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    // 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<Map<String, String>> editComment(@RequestBody String contents, @PathVariable Long commentId) {
        Map<String, String> map = new HashMap<>();
        int editResult = commentService.updateComment(commentId, contents);
        if( editResult != 0) {
            map.put("editResult", "ok");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        map.put("editResult", "fail");
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Map<String, String>> deleteComment(@PathVariable("commentId") Long commentId) {
        Map<String, String> map = new HashMap<>();
        int deleteResult = commentService.deleteById(commentId);
        if( deleteResult != 0) {
            map.put("deleteResult", "ok");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        map.put("editResult", "fail");
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
