package com.cmpe275.Lab2.entity;


import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "PLAYER")
public class Player {
    @Id
    @GeneratedValue
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String description;
    private Address address;
    @ManyToOne(fetch = FetchType.LAZY) // Decide lazy or eager
    private Team team;
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "OPPONENTS")//, joinColumns = @JoinColumn(name = "player1"), inverseJoinColumns = @JoinColumn(name = "player2"))
    private List<Player> opponents;
}

