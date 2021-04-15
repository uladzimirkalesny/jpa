package com.uladzimirkalesny.jpa.model.compositionEx;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Address {

    @Column(nullable = false)
    protected String street;

    protected City city;

}
