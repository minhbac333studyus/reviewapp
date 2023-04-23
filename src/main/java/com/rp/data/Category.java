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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table
@EqualsAndHashCode(exclude = {"id", "topics"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"topics"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(targetEntity = Topic.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, mappedBy = "category")
    @JsonManagedReference
    private List<Topic> topics = new ArrayList<>();

    @UpdateTimestamp
    private Date LastUpdate;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    public Category(String name, List<Topic> topics) {
        super();
        this.name = name;
        this.topics = topics;
    }

    public void addTopic(Topic tp1) {
        tp1.setCategory(this);
        this.topics.add(tp1);
    }

    public void removeTopic(Topic tp1) {
        this.topics.remove(tp1);
        tp1.setCategory(null);
    }
}
