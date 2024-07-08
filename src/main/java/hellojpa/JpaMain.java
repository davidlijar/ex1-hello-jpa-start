package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Team team = new Team();
            team.setName("A");
            em.persist(team);

            Member member = new Member();
            member.setUsername("Lijar");
            member.setTeam(team);
            em.persist(member);

            //it is automatically set in Member class setTeam()
            //team.getMembers().add(member);

            //with this the below(em.find)  is from DB query
            //without this the below(em.find)  is from 1차 캐시 query
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getTeam().getName());

            List<Member> members =  findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

        em.close();
        emf.close();
    }
}
