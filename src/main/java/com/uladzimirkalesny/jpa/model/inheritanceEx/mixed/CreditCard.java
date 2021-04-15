package com.uladzimirkalesny.jpa.model.inheritanceEx.mixed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@Entity
//@SecondaryTable(
//        name = "CREDIT_CARDS",
//        pkJoinColumns = @PrimaryKeyJoinColumn(name = "CREDIT_CARD_ID"))
//@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {

    //  @NotNull - ignore, cuz SINGLE_TABLE
    @Column(table = "CREDIT_CARDS", nullable = false)
    protected String cardNumber;

    @Column(table = "CREDIT_CARDS", nullable = false)
    protected String expMonth;

    @Column(table = "CREDIT_CARDS", nullable = false)
    protected String expYear;

}
