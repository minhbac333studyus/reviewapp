package com.rp.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table
@EqualsAndHashCode(exclude = {"id", "category"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"category" })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @UpdateTimestamp
    private Date LastUpdate;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    public Topic(String name, Category category) {
        super();
        this.name = name;
        this.category = category;
    }
}
