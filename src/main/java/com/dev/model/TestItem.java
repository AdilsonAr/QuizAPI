package com.dev.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TestItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="testId")
	private Test test;
	@ManyToOne
	@JoinColumn(name="questionId")
	private Question question;
	private boolean solvedCorrectly;
	private boolean solved;
	public TestItem(Test test, Question question, boolean solvedCorrectly, boolean solved) {
		super();
		this.test = test;
		this.question = question;
		this.solvedCorrectly = solvedCorrectly;
		this.solved = solved;
	}
	
}
