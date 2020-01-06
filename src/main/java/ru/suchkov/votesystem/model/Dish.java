package ru.suchkov.votesystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "dishes")
public class Dish {
}
