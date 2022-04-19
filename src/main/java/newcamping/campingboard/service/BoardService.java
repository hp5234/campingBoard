package newcamping.campingboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newcamping.campingboard.domain.board.BoardDTO;
import newcamping.campingboard.domain.board.BoardMapper;
import newcamping.campingboard.web.board.BoardForm;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    // 게시판 처리 로직

    private final BoardMapper boardMapper;

    // 유효성 검사
    public int check(String latitude, String longitude) {
        return 0;
    }

    // 추가
    public Long save(BoardForm boardForm){

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setCreateTime(LocalDateTime.now());
        boardDTO.setUpdateTime(LocalDateTime.now());
        boardDTO.setLatitude(boardForm.getLatitude());
        boardDTO.setLongitude(boardForm.getLongitude());
        boardDTO.setOwnerId(boardForm.getOwnerId());
        boardDTO.setTitle(boardForm.getTitle());
        boardDTO.setContents(boardForm.getContents());
        boardDTO.setNoticeCount(0L);

        int result = boardMapper.insert(boardDTO);

        // 입력한 board id 반환
        return boardDTO.getId();
    }

    // 전체 조회
    public List<BoardDTO> findAll(){
        return boardMapper.selectAll();
    }

    // 단일 조회
    public BoardDTO findById(Long id){
        return boardMapper.selectOne(id);
    }

    // 위도 경도 정보 (위치)로 조회
    public int findBySpace( String latitude, String longitude) {
        return boardMapper.selectBySpace(latitude, longitude);
    }

    // 수정
    public int updateBoard(BoardDTO boardDTO){
        boardDTO.setUpdateTime(LocalDateTime.now());
        return boardMapper.update(boardDTO);
    }

    // 삭제
    public int deleteById(Long id){
        return boardMapper.delete(id);
    }
}
