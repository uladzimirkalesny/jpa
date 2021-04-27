package com.uladzimirkalesny.jpa.model.proxiesEx.eagerloading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;

    private String name;

}
