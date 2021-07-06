package com.SpringFirst.Dto;

import java.util.Date;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.SpringFirst.Model.Genre;

import lombok.Data;

@Data
public class MovieDto {
	
	private long id;
	
	@NotNull(message ="Name is Required")
	@Size (min =5 , message ="Name must be at least 5 characters long")
	private String name;
	
	@NotNull(message ="Director is Required")
	@Size (min =5 , message ="Director must be at least 5 characters long")
	private String director;
	
	
	@Range(min = 1995, max =2021,message ="Between 1995 2021")
	private long year;
	
	private Genre genre ;
	
	private Date createAt = new Date();
	
	private Date updateAt;

}
