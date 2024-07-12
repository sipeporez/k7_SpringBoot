package com.rubypaper.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(value=TemporalType.TIMESTAMP)
	@Default
	private Date createDate = new Date();
	@Default
	private Long cnt = 0L;
}
