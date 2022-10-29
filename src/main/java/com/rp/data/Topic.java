package com.rp.data;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@EqualsAndHashCode(exclude = {"id","category","topicPages"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"category","topicPages"})
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToOne(targetEntity = Category.class,fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Category category; 
	@ManyToMany(targetEntity = TopicPage.class,fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<TopicPage> topicPages;
	@UpdateTimestamp
	private Date LastUpdate; 
	@CreationTimestamp
	@Column(updatable = false)
	private Date createdDate; 
	
	public Topic(String name, Category category, Set<TopicPage> topicPages) {
		super();
		this.name = name;
		this.category = category;
		this.topicPages = topicPages;
	}
	
}
