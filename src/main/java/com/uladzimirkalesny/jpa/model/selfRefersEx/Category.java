package com.uladzimirkalesny.jpa.model.selfRefersEx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "CATEGORIES")
@NamedNativeQueries(
        @NamedNativeQuery(
                name = "findAllCategoriesWithLevel",
                query = "with CATEGORY_LINK(ID, TITLE, CATEGORY_PARENT_ID, PATH, LEVEL) as (" +
                        "select ID, TITLE, CATEGORY_PARENT_ID, '/' || TITLE, 0 " +
                        "from CATEGORIES where CATEGORY_PARENT_ID is null " +
                        "union all " +
                        "select c.ID, c.TITLE, c.CATEGORY_PARENT_ID, cl.PATH || '/' || c.TITLE, cl.LEVEL + 1 " +
                        "from CATEGORY_LINK cl join CATEGORIES c on cl.ID = c.CATEGORY_PARENT_ID) " +
                        "select ID, TITLE, CATEGORY_PARENT_ID, PATH, LEVEL " +
                        "from CATEGORY_LINK " +
                        "order by ID",
                resultSetMapping = "CategoryResult"
        )
)
@SqlResultSetMapping(
        name = "CategoryResult",
        entities = {
                @EntityResult(entityClass = com.uladzimirkalesny.jpa.model.selfRefersEx.Category.class,
                        fields = {
                                @FieldResult(name = "id", column = "ID"),
                                @FieldResult(name = "title", column = "TITLE"),
                                @FieldResult(name = "parentCategory", column = "CATEGORY_PARENT_ID"),
                        })},
        columns = {
                @ColumnResult(name = "PATH"),
                @ColumnResult(name = "LEVEL", type = Long.class)
        }
)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_PARENT_ID",
            foreignKey = @ForeignKey(name = "FK_CATEGORY_PARENT_ID"))
    private Category parentCategory;

}
