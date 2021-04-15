package com.uladzimirkalesny.jpa.model.inheritanceEx.table_per_class;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

@AttributeOverride(
        name = "owner",
        column = @Column(name = "BANK_ACCOUNT_OWNER", nullable = false))
//@Entity
public class BankAccount extends BillingDetails {

    @NotNull
    protected String account;

    @NotNull
    protected String bankName;

    @NotNull
    protected String swift;

}
