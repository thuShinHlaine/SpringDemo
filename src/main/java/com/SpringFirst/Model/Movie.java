package com.SpringFirst.Model;



import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Formula;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Movie extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Movie()
	{
		
	}
	public Movie(Long id, String name, String director,Genre genre, Long year)
	{
		this.setId(id);
		this.name = name;
		this.director = director;
		this.genre = genre;
		this.year = year;
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	private String name;
	
	private String director;
	
	private Long year;
	
	@Enumerated(EnumType.STRING)
	private Genre genre ;
	
	
	@Formula("year(now()) - year")
	private Long howOld;
}
