package com.uladzimirkalesny.jpa.model.inheritanceEx.single_table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

@AttributeOverride(
        name = "owner",
        column = @Column(name = "BANK_ACCOUNT_OWNER", nullable = false))
//@Entity
@DiscriminatorValue("BANK_ACCOUNT")
public class BankAccount extends BillingDetails {

    @NotNull
    protected String account;

    @NotNull
    protected String bankName;

    @NotNull
    protected String swift;

}
