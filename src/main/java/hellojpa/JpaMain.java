package hellojpa;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Member member = new Member();
            member.setUsername("Member1");
            member.setHomeAddress(new Address("city1", "street", "zipcode"));

            member.getFavFoods().add("Noodle");
            member.getFavFoods().add("Chicken");

            member.getAddressHistory().add(new Address("old1", "street", "zipcode"));
            member.getAddressHistory().add(new Address("old2", "street", "zipcode"));

            em.persist(member);
            
            em.flush();
            em.clear();

            System.out.println("=============start============");
            Member findMember = em.find(Member.class, member.getId());

            System.out.println("===========");
            findMember.getAddressHistory().remove(new Address("old1", "street", "zipcode"));
            //findMember.getAddressHistory().add(new Address("newCity23", "street", "zipcode"));





            ;    tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();

        em.close();
        emf.close();
    }
}
