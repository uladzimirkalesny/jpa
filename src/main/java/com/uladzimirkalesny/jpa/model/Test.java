package com.uladzimirkalesny.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@Entity
@Table(name = "TESTS")
public class Test {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @Basic(optional = false)
    private String text; // false - text varchar(255) not null, // true - text varchar(255),

}
