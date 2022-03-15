package newcamping.campingboard.repository;

import newcamping.campingboard.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() throws Exception {
        // given
        Member member = new Member();

        member.setLoginId("hp5234");
        member.setPassword("ekekek002");
        member.setName("전진우");
        member.setEmail("hp5234@naver.com");


        // when

        Long savedId = memberRepository.save(member);
        Member findedMember = memberRepository.findById(savedId);


        // then
        Assertions.assertThat(findedMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findedMember.getLoginId()).isEqualTo(member.getLoginId());


    }


}