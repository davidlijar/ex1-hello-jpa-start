package hellojpa;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //InheritanceType.SINGLE_TABLE
@DiscriminatorColumn
public class Item extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private  String name;
    private int price;


}
