package com.uladzimirkalesny.jpa.model.proxiesEx.lazyloading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

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

    // @ElementCollection, @OneToMany and @ManyToMany has lazy loading by default
    @OneToMany(mappedBy = "item",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
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
