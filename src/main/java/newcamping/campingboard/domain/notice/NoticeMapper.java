package newcamping.campingboard.domain.notice;

import newcamping.campingboard.domain.comment.CommentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {

    /**
     * 게시물 Mapper
     */

    // 추가
    @Insert("INSERT INTO board(create_time, update_time, board_id, owner_id, title, contents, comment_count, view_count, like_count, unlike_count) " +
            "VALUES #{notice.createTime},#{notice.updateTime},#{notice.boardId},#{notice.ownerId},#{notice.title},#{notice.contents},#{notice.commentCount},#{notice.viewCount},#{notice.likeCount},#{notice.unlikeCount}")
    @Options(useGeneratedKeys = true, keyProperty = "id") // member 에 생성된 키를 id 라는 프로퍼티에 설정해달라는 옵션
    int insert(NoticeDTO notice); // insert 문은 입력된 데이터 갯수 반환, 실패시 0 return

    // 게시판 소속 게시물 전체 조회
    @Select("SELECT * FROM notice WHERE notice_id=#{boardId}")
    List<NoticeDTO> selectAll();

    // 단일 조회
    @Select("SELECT * FROM notice WHERE id=#{noticeId}")
    NoticeDTO selectOne(Long noticeId);

    // 게시물 수정
    @Update("UPDATE notice SET update_time=#{notice.updateTime},title=#{notice.title}, contents=#{notice.contents} WHERE id=#{noticeId}")
    int update(NoticeDTO notice);

    // 조회수 증가
    @Update("UPDATE notice SET view_count=view_count+1 WHERE id=#{noticeId}")
    int plusView(Long noticeId);

    // 댓글 수 증가
    @Update("UPDATE notice SET comment_count=comment_count+1 WHERE id=#{noticeId}")
    int plusComment(Long noticeId);

    // 댓글 수 감소
    @Update("UPDATE notice comment_count=comment_count-1 WHERE id=#{noticeId}")
    int subComment(Long noticeId);

    // 좋아요 증가
    @Update("UPDATE notice SET like_count=like_count+1 WHERE id=#{noticeId}")
    int plusLike(Long noticeId);

    // 싫어요 감소
    @Update("UPDATE notice SET like_count=like_count-1 WHERE id=#{noticeId}")
    int subLike(Long noticeId);

    // 싫어요 증가
    @Update("UPDATE notice SET unlike_count=unlike_count+1 WHERE id=#{noticeId}")
    int plusUnlike(Long noticeId);

    // 싫어요 감소
    @Update("UPDATE notice SET unlike_count=unlike_count-1 WHERE id=#{noticeId}")
    int subUnlike(Long noticeId);

    // 삭제
    @Delete("DELETE FROM notice WHERE id=#{noticeId}")
    int delete(long noticeId);
}
