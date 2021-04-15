package com.uladzimirkalesny.jpa.model.associationsEx.OneToOne.joinTable;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@Entity
@Table(name = "SHIPMENTS")
public class Shipment {
    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    protected Long id;

    @NotNull
    protected LocalDate createdOn = LocalDate.now();

    @NotNull
    protected ShipmentState shipmentState = ShipmentState.TRANSIT;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ITEM_SHIPMENT", // Required!
            joinColumns = @JoinColumn(name = "SHIPMENT_ID"),  // Defaults to ID
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID",  // Defaults to AUCTION_ID
                    nullable = false,
                    unique = true)
    )
    protected Item auction;
}
