package com.uladzimirkalesny.jpa.model.associationsEx.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@ToString(exclude = "items")
@EqualsAndHashCode(exclude = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    protected Long id;

    protected String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    protected Set<Item> items = new HashSet<>();

    public void addItem(Item item) {
        this.items.add(item);
    }

}
