package com.uladzimirkalesny.jpa.model.associationsEx.OneToOne.sharedPrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@Entity
@Table(name = "USERS")
public class User {

    @Id
    private Long id;

    protected String username;

    @OneToOne(fetch = FetchType.LAZY,  // Defaults to EAGER
            optional = false // Required for lazy loading with proxies!
    )
    @PrimaryKeyJoinColumn
    protected Address shippingAddress;

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
