package com.uladzimirkalesny.jpa.model.inheritanceEx.single_table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CONCRETE_TYPE")
public abstract class BillingDetails {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    protected Long id;

    @NotNull
    @Column(nullable = false)
    protected String owner;
}
