package com.uladzimirkalesny.jpa.model.proxiesEx.eagerloading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@EqualsAndHashCode(exclude = "bidder")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "BIDS")
public class Bid {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;

    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BIDDER_ID", nullable = false)
    private User bidder;

}
