package newcamping.campingboard.service;

import lombok.RequiredArgsConstructor;
import newcamping.campingboard.domain.board.BoardDTO;
import newcamping.campingboard.domain.notice.NoticeDTO;
import newcamping.campingboard.domain.notice.NoticeMapper;
import newcamping.campingboard.web.board.BoardForm;
import newcamping.campingboard.web.notice.NoticeForm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    // 게시물 처리 로직

    private final NoticeMapper noticeMapper;

    // 추가
    public Long save(NoticeForm noticeForm){

        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setCreateTime(LocalDateTime.now());
        noticeDTO.setUpdateTime(LocalDateTime.now());
        noticeDTO.setBoardId(noticeForm.getBoardId());
        noticeDTO.setOwnerId(noticeForm.getOwnerId());
        noticeDTO.setTitle(noticeForm.getTitle());
        noticeDTO.setContents(noticeForm.getContents());
        noticeDTO.setCommentCount(0L);
        noticeDTO.setViewCount(0L);
        noticeDTO.setLikeCount(0L);
        noticeDTO.setUnlikeCount(0L);

        int result = noticeMapper.insert(noticeDTO);

        // 입력한 board id 반환
        return noticeDTO.getId();
    }

    // 전체 조회
    public List<NoticeDTO> findAll(){
        return noticeMapper.selectAll();
    }

    // 단일 조회
    public NoticeDTO findById(Long noticeId){
        return noticeMapper.selectOne(noticeId);
    }

    // 수정
    public int updateNotice(NoticeDTO noticeDTO){
        noticeDTO.setUpdateTime(LocalDateTime.now());
        return noticeMapper.update(noticeDTO);
    }

    // 삭제
    public int deleteById(Long noticeId){
        return noticeMapper.delete(noticeId);
    }
}
