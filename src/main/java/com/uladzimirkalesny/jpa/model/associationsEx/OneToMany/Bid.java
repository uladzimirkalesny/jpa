package com.uladzimirkalesny.jpa.model.associationsEx.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@EqualsAndHashCode(exclude = {"id", "item"})
@Data
@NoArgsConstructor
@AllArgsConstructor

//@Entity
@Table(name = "BIDS")
public class Bid {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    @Column(name = "BID_ID")
    protected Long id;

    protected int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    protected Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BIDDER_ID", nullable = false)
    protected User bidder;

}
