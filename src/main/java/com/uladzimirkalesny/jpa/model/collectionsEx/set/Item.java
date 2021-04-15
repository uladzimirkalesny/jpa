package com.uladzimirkalesny.jpa.model.collectionsEx.set;

import com.uladzimirkalesny.jpa.constant.JpaConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@Entity
@Table(name = "ITEMS")
public class Item {
    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    protected Long id;

    protected String name;

    @ElementCollection
    @CollectionTable(name = "IMAGES")
    @AttributeOverride(
            name = "filename",
            column = @Column(name = "FILENAME", nullable = false)
    )
    protected Set<Image> images = new HashSet<>();

}
