package com.uladzimirkalesny.jpa.model.inheritanceEx.associations.ManyToOne.unidirectional;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetails {
    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    protected Long id;

    @NotNull
    protected String owner;
}
