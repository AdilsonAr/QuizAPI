package com.dev.model;

public enum Difficulties {
	EASY(1, "easy"),
	MEDIUM(2, "medium"),
	HARD(3, "hard");
	
	private final int id;
	private final String label;
	
	private Difficulties(int id, String label) {
		this.label = label;
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public int getId() {
		return id;
	}

}
