package com.rp.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Retry.Topic;

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
@EqualsAndHashCode(exclude = {"id","topicPage"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"topicPage"})
public class ProductOnTopic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(targetEntity = TopicPage.class,fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TopicPage topicPage;
	private Integer ratePoint;
	private String reasonToBuy;
	private String reasonToAvoid;
	private String name;
    @ElementCollection(targetClass=String.class)
	private List<String> linkReferences;
	private String description;
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
	
}
