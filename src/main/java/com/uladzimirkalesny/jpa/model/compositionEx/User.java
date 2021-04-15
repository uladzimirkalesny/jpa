package com.uladzimirkalesny.jpa.model.compositionEx;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;

    protected Address homeAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "BILLING_STREET", nullable = false)),
            @AttributeOverride(name = "city.name", column = @Column(name = "BILLING_CITY", nullable = false)),
            @AttributeOverride(name = "city.zipCode", column = @Column(name = "BILLING_ZIPCODE", nullable = false)),
            @AttributeOverride(name = "city.country", column = @Column(name = "BILLING_COUNTRY", nullable = false))
    })
    protected Address billingAddress;

    @NotNull
    @Convert(converter = UserNameConverter.class)
    protected String userName;

}
