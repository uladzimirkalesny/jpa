package com.uladzimirkalesny.jpa.model.associationsEx.OneToOne.sharedPrimaryKey;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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

//@Entity
@Table(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;

    @NotNull
    protected String street;
    @NotNull
    protected String zipcode;
    @NotNull
    protected String city;

}
