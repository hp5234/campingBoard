package newcamping.campingboard.repository;


import lombok.extern.slf4j.Slf4j;
import newcamping.campingboard.domain.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 저장
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    // 조회 - id
    public Member findById(Long id){
        return em.find(Member.class, id);
    }


}
