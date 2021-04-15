package com.uladzimirkalesny.jpa.model.associationsEx.OneToOne.joinTable;

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

//@Entity
@Table(name = "ITEMS")
public class Item {
    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    protected Long id;

    protected String name;
}
