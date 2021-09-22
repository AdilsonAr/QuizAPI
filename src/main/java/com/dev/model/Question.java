package com.dev.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "TEXT")
	private String question;
	@OneToMany(mappedBy = "question", orphanRemoval = true)
	private List<Answer> answers;
	@ManyToOne
	@JoinColumn(name = "subCategoryId")
	private SubCategory subCategory;
	@OneToMany(mappedBy = "question", orphanRemoval = true)
	private List<TestItem> testItems;
}
