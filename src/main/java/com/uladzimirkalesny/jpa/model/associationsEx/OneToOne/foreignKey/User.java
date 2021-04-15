package com.uladzimirkalesny.jpa.model.associationsEx.OneToOne.foreignKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    protected Long id;

    protected String username;

    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false, // NOT NULL
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(unique = true) // Defaults to SHIPPINGADDRESS_ID
    protected Address shippingAddress;

}
