package com.uladzimirkalesny.jpa.model.associationsEx.ManyToMany.intermediateEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static com.uladzimirkalesny.jpa.constant.JpaConstants.ID_GENERATOR;

@ToString(exclude = "categorizedItems")
@EqualsAndHashCode(exclude = "categorizedItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue(generator = ID_GENERATOR)
    protected Long id;

    protected String name;

    @OneToMany(mappedBy = "item")
    protected Set<CategorizedItem> categorizedItems = new HashSet<>();

}
