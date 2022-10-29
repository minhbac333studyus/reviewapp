package com.rp.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Retry.Topic;

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
@EqualsAndHashCode(exclude = {"id","topicPages"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"topicPages"})
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fullName;
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
	private List<TopicPage> topicPages;
	private String introduction;
	private String email;
	@UpdateTimestamp
	private Date LastUpdate; 
	@CreationTimestamp
	@Column(updatable = false)
	private Date createdDate;
	public Author(String fullName, List<TopicPage> topicPages, String introduction, String email) {
		super();
		this.fullName = fullName;
		this.topicPages = topicPages;
		this.introduction = introduction;
		this.email = email;
	}
}
