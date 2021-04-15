package com.uladzimirkalesny.jpa.model.inheritanceEx.associations.OneToMany.bidirectional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "user")
    private Set<BillingDetails> billingDetails = new HashSet<>();

}
