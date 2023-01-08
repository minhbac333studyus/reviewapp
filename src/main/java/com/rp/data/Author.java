package com.rp.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@EqualsAndHashCode(exclude = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
//	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE},mappedBy = "author") 
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private List<TopicPage> topicPages = new ArrayList<>();

    @Column( columnDefinition="LONGTEXT" )
    private String introduction;
    private String email;
    @UpdateTimestamp
    private Date LastUpdate;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    public Author(String fullName, String introduction, String email) {

        this.fullName = fullName;
//		this.topicPages = topicPages;
        this.introduction = introduction;
        this.email = email;
    }
//	public void addTopicPages(TopicPage tp1) {
//		tp1.setAuthor(this);
//		this.topicPages.add(tp1);
//	}
//	public void removeTopicPages(TopicPage tp1) {
//		this.topicPages.remove(tp1);
//		tp1.setAuthor(null);
//	}
// 
}
