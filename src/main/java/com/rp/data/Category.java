package com.rp.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@EqualsAndHashCode(exclude = { "id", "topics" })
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "topics" })
public class Category { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToMany(targetEntity = Topic.class, fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "topic_id")
	private List<Topic> topics;
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
}
