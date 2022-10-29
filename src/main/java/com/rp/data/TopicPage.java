package com.rp.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp; 

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rp.controller.CommentOnTopicController;
import com.rp.services.CommentOnTopicService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@Entity
@Table
@EqualsAndHashCode(exclude = { "id", "productOnTopics", "author","topics" })
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "productOnTopics", "author","topics" })
public class TopicPage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String topicTitle;
	private String topicHeaderContent;
	private String topicMiddleContent;
	private String topicFooterContent;
	private String topicContent;
	@OneToMany(targetEntity = ProductOnTopic.class, fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "productOnTopic_id")
	private List<ProductOnTopic> productOnTopics;
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "author_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Author author;
	@ManyToMany(targetEntity = Topic.class,fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "topic_topicPage", 
				joinColumns = @JoinColumn(name = "topic_page_id", referencedColumnName = "id"), 
				inverseJoinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id"))
	private Set< Topic> topics;

	@UpdateTimestamp
	private Date LastUpdate;
	@CreationTimestamp
	@Column(updatable = false)
	private Date createdDate;
	public TopicPage(String topicTitle, String topicHeaderContent, String topicMiddleContent, String topicFooterContent,
			String topicContent, List<ProductOnTopic> productOnTopics, Author author, Set<Topic> topics) {
		super();
		this.topicTitle = topicTitle;
		this.topicHeaderContent = topicHeaderContent;
		this.topicMiddleContent = topicMiddleContent;
		this.topicFooterContent = topicFooterContent;
		this.topicContent = topicContent;
		this.productOnTopics = productOnTopics;
		this.author = author;
		this.topics = topics;
	}

 

}
