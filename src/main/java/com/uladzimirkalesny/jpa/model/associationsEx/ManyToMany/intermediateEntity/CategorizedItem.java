package com.uladzimirkalesny.jpa.model.associationsEx.ManyToMany.intermediateEntity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@Entity
@Table(name = "CATEGORY_ITEM")
@org.hibernate.annotations.Immutable
public class CategorizedItem {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder

    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "CATEGORY_ID")
        protected Long categoryId;

        @Column(name = "ITEM_ID")
        protected Long itemId;

    }

    @EmbeddedId
    protected Id id = new Id();

    @Column(updatable = false)
    @NotNull
    protected String addedBy;

    @Column(updatable = false)
    @NotNull
    protected LocalDate addedOn = LocalDate.now();

    @ManyToOne
    @JoinColumn(
            name = "CATEGORY_ID",
            insertable = false, updatable = false)
    protected Category category;

    @ManyToOne
    @JoinColumn(
            name = "ITEM_ID",
            insertable = false, updatable = false)
    protected Item item;

    public CategorizedItem(String addedByUsername,
                           Category category,
                           Item item) {

        // Set fields
        this.addedBy = addedByUsername;
        this.category = category;
        this.item = item;

        // Set identifier values
        this.id.categoryId = category.getId();
        this.id.itemId = item.getId();

        // Guarantee referential integrity if made bidirectional
        category.getCategorizedItems().add(this);
        item.getCategorizedItems().add(this);
    }
}