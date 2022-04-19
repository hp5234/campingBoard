package newcamping.campingboard.domain.member;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    /*
     * 회원 Mapper
     */

    // 추가
    @Insert("INSERT INTO memberDTO(login_id, password, name, email) VALUES (#{memberDTO.loginId},#{memberDTO.password},#{memberDTO.name},#{memberDTO.email})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // memberDTO 에 생성된 키를 id 라는 프로퍼티에 설정해달라는 옵션
    int insert(MemberDTO memberDTO); // insert 문은 입력된 데이터 갯수 반환, 실패시 0 return

    // 전체 조회
    @Select("SELECT * FROM member")
    @Results(id="MemberDTO",
            value= {
                    @Result(property = "loginId", column = "login_id")
            }
    )
    List<MemberDTO> selectAll();

    // View 객체 전체 조회
    @Select("SELECT id, login_id, name, email FROM member")
    @Results(id="MemberViewDTO",
            value= {
                    @Result(property = "loginId", column = "login_id")
            }
    )
    List<MemberViewDTO> selectViewAll();

    // id 로 단일 조회
    @Select("SELECT * FROM member WHERE id=#{memberId}")
    @ResultMap("MemberDTO")
    MemberDTO selectById(Long memberId);

    // loginId 로 단일 조회
    @Select("SELECT * FROM member WHERE login_id=#{loginId}")
    @ResultMap("MemberDTO")
    Optional<MemberDTO> selectByLoginId(String loginId);

    // View 객체 id 로 단일 조회
    @Select("SELECT id, login_id, name, email FROM member WHERE id=#{memberId}")
    @ResultMap("MemberViewDTO")
    MemberViewDTO selectViewById(Long memberId);

    // View 객체 loginId 로 단일 조회
    @Select("SELECT id, login_id, name, email FROM member WHERE login_id=#{loginId}")
    @ResultMap("MemberViewDTO")
    Optional<MemberViewDTO> selectViewByLoginId(String loginId);

    // 수정
    @Update("UPDATE member SET password={member.password}, email={member.email}) WHERE id=#{member.id}")
    int update(MemberDTO member);

    // 삭제
    @Delete("DELETE FROM member WHERE id=#{memberId}")
    int delete(Long memberId);
}
