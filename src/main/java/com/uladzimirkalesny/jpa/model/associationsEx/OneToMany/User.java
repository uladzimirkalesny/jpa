package com.uladzimirkalesny.jpa.model.associationsEx.OneToMany;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    protected Long id;

    @NotNull
    @Column(name = "BIDDER_NAME")
    protected String name;

    @OneToMany(mappedBy = "bidder")
    protected Set<Bid> bids = new HashSet<>();

    public void addBid(Bid bid) {
        bids.add(bid);
        bid.setBidder(this);
    }

    public void removeBid(Bid bid) {
        bids.remove(bid);
        bid.setBidder(null);
    }
}
