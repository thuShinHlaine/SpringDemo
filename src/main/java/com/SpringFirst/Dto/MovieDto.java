package com.SpringFirst.Dto;

import java.util.Date;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.SpringFirst.Model.Genre;

import lombok.Data;

@Data
public class MovieDto {
	
	private Long id;
	
	@NotNull
	@Size (min =5 , message ="Name must be at least 5 characters long")
	private String name;
	
	@NotNull
	@Size (min =5 , message ="Director must be at least 5 characters long")
	private String director;
	
	@NotNull(message="Year is Required")
	@Range(min = 1995, max =2021,message ="Between 1995 2021")
	private Long year;
	
	private Genre genre ;
	
	private Long howOld;
	
	private Date createAt = new Date();
	
	private Date updateAt = new Date();

}
