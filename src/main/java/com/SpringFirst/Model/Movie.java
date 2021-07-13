package com.SpringFirst.Model;



import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movie implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	private String name;
	
	private String director;
	
	private Long year;
	
	@Enumerated(EnumType.STRING)
	private Genre genre ;
	
	@Column(name="create_at")
	private Date createAt = new Date();
	
	@Column(name="update_at")
	private Date updateAt;

	
	

}
