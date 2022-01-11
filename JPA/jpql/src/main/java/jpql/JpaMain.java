package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);


            Member member1 = new Member();
            member1.setTeam(teamA);
            member1.setUsername("회원1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setTeam(teamA);
            member2.setUsername("회원2");
            em.persist(member2);

            Member member3 = new Member();
            member3.setTeam(teamB);
            member3.setUsername("회원3");
            em.persist(member3);

            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            em.clear();

            Member findMember = em.find(Member.class, member1.getId());

            System.out.println("findMember.getAge() = " + findMember.getAge());

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

}

