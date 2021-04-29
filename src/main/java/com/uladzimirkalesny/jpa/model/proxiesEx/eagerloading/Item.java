package com.uladzimirkalesny.jpa.model.proxiesEx.eagerloading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@EqualsAndHashCode(exclude = {"seller", "bids"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SELLER_ID")
    private User seller;

    @OneToMany(mappedBy = "item",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<Bid> bids = new HashSet<>();


    public void addBid(Bid bid) {
        bids.add(bid);
        bid.setItem(this);
    }

    public void removeBid(Bid bid) {
        bids.remove(bid);
        bid.setItem(null);
    }

}
