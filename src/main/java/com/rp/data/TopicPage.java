package com.rp.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity
@Table
@EqualsAndHashCode(exclude = {"id", "productOnTopics", "author", "topics"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"productOnTopics", "author", "topics"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TopicPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column( columnDefinition="LONGTEXT" )
    private String topicTitle;
    @Column( columnDefinition="LONGTEXT" )
    private String topicHeaderContent;
    @Column( columnDefinition="LONGTEXT" )
    private String topicMiddleContent;
    @Column( columnDefinition="LONGTEXT" )
    private String topicFooterContent;
    @Column( columnDefinition="LONGTEXT" )
    private String topicContent;
    @OneToMany(targetEntity = ProductOnTopic.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, mappedBy = "topicPage")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ProductOnTopic> productOnTopics = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Author author;

    @ManyToOne(targetEntity = Topic.class, fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "topic_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Topic topic;

    @UpdateTimestamp
    private Date LastUpdate;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    public TopicPage(String topicTitle, String topicHeaderContent, String topicMiddleContent, String topicFooterContent,
                     String topicContent, List<ProductOnTopic> productOnTopics, Author author, Topic topic) {
        super();
        this.topicTitle = topicTitle;
        this.topicHeaderContent = topicHeaderContent;
        this.topicMiddleContent = topicMiddleContent;
        this.topicFooterContent = topicFooterContent;
        this.topicContent = topicContent;
        this.productOnTopics = productOnTopics;
        this.author = author;
        this.topic = topic;
    }

    void addProductOnTopic(ProductOnTopic p) {
        this.productOnTopics.add(p);
        p.setTopicPage(this);
    }

    void removeProductOnTopic(ProductOnTopic p) {
        this.productOnTopics.remove(p);
        p.setTopicPage(null);
    }
}
