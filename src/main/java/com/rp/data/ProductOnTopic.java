package com.rp.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table
@EqualsAndHashCode(exclude = {"id","topicPage"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"topicPage"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProductOnTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = TopicPage.class, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "topicPage_id")
    private TopicPage topicPage;
    private Integer ratePoint;
    @Column( columnDefinition="LONGTEXT" )
    private String reasonToBuy;
    @Column( columnDefinition="LONGTEXT" )
    private String reasonToAvoid;
    @Column( columnDefinition="LONGTEXT" )
    private String name;
    @ElementCollection(targetClass = String.class)
    private List<String> linkReferences;
    @Column( columnDefinition="LONGTEXT" )
    private String description;
    @Column( columnDefinition="LONGTEXT" )
    private String recommendForUser;
    @UpdateTimestamp
    private Date LastUpdate;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    public ProductOnTopic(TopicPage topicPage, Integer ratePoint, String reasonToBuy, String reasonToAvoid,
                          List<String> linkReferences, String description, String recommendForUser) {
        super();
        this.topicPage = topicPage;
        this.ratePoint = ratePoint;
        this.reasonToBuy = reasonToBuy;
        this.reasonToAvoid = reasonToAvoid;
        this.linkReferences = linkReferences;
        this.description = description;
        this.recommendForUser = recommendForUser;
    }

    void addLinkReferences(String link) {
        this.linkReferences.add(link);
    }

    void removeLinkReferences(String link) {
        this.linkReferences.remove(link);
    }
}
