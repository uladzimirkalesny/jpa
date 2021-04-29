package com.uladzimirkalesny.jpa.model.queries.criteria;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Item.class)
public class Item_ {
    public static volatile SingularAttribute<Item, Long> id;
    public static volatile SingularAttribute<Item, String> title;
}
