package com.SpringFirst.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Formula;

import lombok.Data;


@Entity
@Data

public class Actor extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name ="firstName")
	private String firstName;
	
	@Column(name ="lastName")
	private String lastName;
	
	@Formula("concat(first_name,\" \",last_name)")
	private String fullName;

	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
}
