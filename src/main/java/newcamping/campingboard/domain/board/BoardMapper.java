package newcamping.campingboard.domain.board;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    /**
     * 게시판 Mapper
     */

    // 추가
    @Insert("INSERT INTO board(create_time, update_time, latitude, longitude, owner_id, title, contents, notice_count) VALUES (#{board.createTime},#{board.updateTime},#{board.latitude},#{board.longitude},#{board.ownerId},#{board.title},#{board.contents},#{board.noticeCount})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // member 에 생성된 키를 id 라는 프로퍼티에 설정해달라는 옵션
    int insert(BoardDTO board); // insert 문은 입력된 데이터 갯수 반환, 실패시 0 return

    // 전체 조회
    @Select("SELECT * FROM board")
    @Results(id="BoardDTO", value={
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "ownerId", column = "owner_id"),
            @Result(property = "noticeCount", column = "notice_count")
    })
    List<BoardDTO> selectAll();

    // 단일 조회
    @Select("SELECT * FROM board WHERE id=#{id}")
    BoardDTO selectOne(Long id);

    // 위치 정보로 조회
    @Select("SELECT * FROM board WHERE latitude=#{latitude} AND longitude=#{longitude}")
    int selectBySpace(String latitude, String longitude);

    // 수정
    @Update("UPDATE board SET updated_time=#{board.updateTime}, title=#{board.title}, contents=#{board.contents} WHERE id={board.id}")
    int update(BoardDTO board);

    // 게시물 갯수 증가
    @Update("UPDATE board SET notice_count=notice_count+1 WHERE id=#{boardId}")
    int plusCount(Long boardId);

    // 게시물 갯수 감소
    @Update("UPDATE board SET notice_count=notice_count-1 WHERE id=#{boardId}")
    int subCount(Long boardId);

    // 삭제
    @Delete("DELETE FROM board WHERE id=#{id}")
    int delete(@Param("id") long id);
}
