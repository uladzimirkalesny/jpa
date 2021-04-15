package com.uladzimirkalesny.jpa.model.inheritanceEx.associations.ManyToOne.unidirectional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private BillingDetails defaultBilling;

}
