package com.uladzimirkalesny.jpa.model.associationsEx.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    @Column(name = "ITEM_ID")
    protected Long id;

    @Column(nullable = false)
    protected String name;

}
