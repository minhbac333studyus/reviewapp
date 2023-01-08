package com.rp.data;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Retry.Topic;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@EqualsAndHashCode(exclude = {"id","topic"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"topic"})
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class CommentOnTopicPage { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(targetEntity = TopicPage.class,fetch = FetchType.LAZY,optional = false,cascade = {CascadeType.PERSIST})
    @JoinColumn(name="topicPage_id",nullable = false)  
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TopicPage topicPage;
	private String comment;
	@UpdateTimestamp
	private Date LastUpdate; 
	@CreationTimestamp
	@Column(updatable = false)
	private Date createdDate;
	public CommentOnTopicPage(TopicPage topicPage) {
		super();
		this.topicPage = topicPage;
	}
	
}
