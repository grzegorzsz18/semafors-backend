package com.semafors.entity;

import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Token implements Serializable{
	
	@Column(columnDefinition = "BINARY(16)")
	private UUID value;
	private Long creationTime;

	public Token() {
	}

	public UUID getValue() {
		return value;
	}

	public void setValue(UUID value) {
		this.value = value;
	}

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
}
