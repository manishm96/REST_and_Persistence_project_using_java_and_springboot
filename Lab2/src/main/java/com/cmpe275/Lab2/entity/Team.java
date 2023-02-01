package com.cmpe275.Lab2.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "TEAM")
public class Team {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private Address address;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Player> players;
}

