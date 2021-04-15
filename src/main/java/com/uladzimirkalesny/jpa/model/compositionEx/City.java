package com.uladzimirkalesny.jpa.model.compositionEx;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class City {

    @NotNull
    @Column(nullable = false, length = 6)
    protected String zipCode;

    @NotNull
    @Column(nullable = false)
    protected String name;

    @NotNull
    @Column(nullable = false)
    protected String country;


}
