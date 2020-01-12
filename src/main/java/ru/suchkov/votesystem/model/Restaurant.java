package ru.suchkov.votesystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private Collection<Vote> votes;

    @OneToMany(mappedBy = "restaurant")
    private Collection<Dish> dishes;
}
