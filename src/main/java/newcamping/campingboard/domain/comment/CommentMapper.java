package newcamping.campingboard.domain.comment;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 댓글 Mapper
     */

    // 추가
    @Insert("INSERT INTO comment(updated_time, owner_id, contents, notice_id, like_count, unlike_count) VALUES (#{comment.updatedTime},#{comment.ownerId},#{comment.contents},#{comment.noticeId},#{comment.likeCount},#{comment.unlikeCount}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(CommentDTO commentDTO); // insert 문은 입력된 데이터 갯수 반환, 실패시 0 return

    // 게시물 소속 댓글 전체 조회
    @Select("SELECT * FROM comment WHERE notice_id=#{noticeId}")
    List<CommentDTO> selectAllByNoticeId(Long notice_id);

    // 단일 조회
    @Select("SELECT * FROM comment WHERE id=#{commentId}")
    CommentDTO selectById(Long commentId);

    // 댓글 수정
    @Update("UPDATE comment SET updated_time=#{comment.updatedTime}, contents=#{comment.contents} WHERE id=#{comment.commentId}")
    int update(CommentDTO comment);

    // 좋아요 증가
    @Update("UPDATE comment SET like_count=like_count+1 WHERE id=#{commentId}")
    int plusLike(Long commentId);

    // 싫어요 감소
    @Update("UPDATE comment SET like_count=like_count-1 WHERE id=#{commentId}")
    int subLike(Long commentId);

    // 싫어요 증가
    @Update("UPDATE comment SET unlike_count=unlike_count+1 WHERE id=#{commentId}")
    int plusUnlike(Long commentId);

    // 싫어요 감소
    @Update("UPDATE comment SET unlike_count=unlike_count-1 WHERE id=#{commentId}")
    int subUnlike(Long commentId);

    // 삭제
    @Delete("DELETE FROM comment WHERE id=#{commentId}")
    int delete(long commentId);
}
