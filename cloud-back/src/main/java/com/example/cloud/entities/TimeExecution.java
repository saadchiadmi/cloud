package com.example.cloud.entities;

public class TimeExecution {
	
	long index;
	
	long jaccard;
	
	long warshall;
	
	long closeness;

	public TimeExecution() {
		this.index = 0;
		this.jaccard = 0;
		this.warshall = 0;
		this.closeness = 0;
	}

	public TimeExecution(long index, long jaccard, long warshall, long closeness) {
		this.index = index;
		this.jaccard = jaccard;
		this.warshall = warshall;
		this.closeness = closeness;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public long getJaccard() {
		return jaccard;
	}

	public void setJaccard(long jaccard) {
		this.jaccard = jaccard;
	}

	public long getWarshall() {
		return warshall;
	}

	public void setWarshall(long warshall) {
		this.warshall = warshall;
	}

	public long getCloseness() {
		return closeness;
	}

	public void setCloseness(long closeness) {
		this.closeness = closeness;
	}

}
