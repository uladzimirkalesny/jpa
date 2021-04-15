package com.uladzimirkalesny.jpa.model.inheritanceEx.mapped_super_class;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

//@Entity
@AttributeOverride(
        name = "owner",
        column = @Column(name = "BANK_ACCOUNT_OWNER", nullable = false))
public class BankAccount extends BillingDetails {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    protected Long id;

    @NotNull
    protected String account;

    @NotNull
    protected String bankName;

    @NotNull
    protected String swift;
}
