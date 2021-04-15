package com.uladzimirkalesny.jpa.model.associationsEx.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@ToString(exclude = "bids")
@EqualsAndHashCode(exclude = {"bids"})
@Data
@NoArgsConstructor
@AllArgsConstructor

//@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    @Column(name = "ITEM_ID")
    protected Long id;

    @Column(nullable = false)
    protected String name;

    @OneToMany(mappedBy = "item", // Required for bidirectional association
            cascade = {
                    CascadeType.PERSIST/*, // save
                    CascadeType.REMOVE // remove*/
            },
            orphanRemoval = true,
            fetch = FetchType.LAZY) // The default
    protected Set<Bid> bids = new HashSet<>();

    public void addBid(Bid bid) {
        bids.add(bid);
        bid.setItem(this);
    }

    public void removeBid(Bid bid) {
        bids.remove(bid);
        bid.setItem(null);
    }

}
