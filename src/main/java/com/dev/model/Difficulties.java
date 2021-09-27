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
	
	public static Difficulties getDifficultie(int id) {
		Difficulties d=null;
		switch(id) {
		case 1:
			d=Difficulties.EASY;
			break;
		case 2:
			d=Difficulties.MEDIUM;
			break;
		case 3:
			d=Difficulties.HARD;
			break;
			default:
			throw new IllegalArgumentException("The difficulty level is not admited");
		}
		return d;
	}

}
