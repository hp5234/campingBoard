package newcamping.campingboard.service;

import lombok.RequiredArgsConstructor;
import newcamping.campingboard.domain.comment.CommentDTO;
import newcamping.campingboard.domain.comment.CommentMapper;
import newcamping.campingboard.web.comment.CommentForm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    // 댓글 추가
    public Long save(CommentForm commentForm) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setOwnerId(commentForm.getOwnerId());
        commentDTO.setContents(commentForm.getContents());
        commentDTO.setNoticeId(commentForm.getNoticeId());
        commentDTO.setUpdatedTime(LocalDateTime.now());
        commentDTO.setLikeCount(0L);
        commentDTO.setUnlikeCount(0L);

        int result = commentMapper.insert(commentDTO);
        return commentDTO.getId();
    }

    // 전체 조회
    public List<CommentDTO> findAllByNoticeId( Long noticeId){
        return commentMapper.selectAllByNoticeId(noticeId);
    }

    // 수정
    public int updateComment( CommentDTO commentDTO) {
        commentDTO.setUpdatedTime(LocalDateTime.now());
        return commentMapper.update(commentDTO);
    }

    // 삭제
    public int deleteById( Long commentId) {
        return commentMapper.delete(commentId);
    }
}
