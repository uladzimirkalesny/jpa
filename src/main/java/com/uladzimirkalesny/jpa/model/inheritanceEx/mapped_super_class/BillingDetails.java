package com.uladzimirkalesny.jpa.model.inheritanceEx.mapped_super_class;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class BillingDetails {

    @NotNull
    protected String owner;

}
