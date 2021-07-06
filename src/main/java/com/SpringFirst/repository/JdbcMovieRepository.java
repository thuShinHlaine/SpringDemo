package com.SpringFirst.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.SpringFirst.Model.Genre;
import com.SpringFirst.Model.Movie;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcMovieRepository implements MovieRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Movie> findAll() {
		log.info("JDBC Template" + jdbcTemplate);

		return this.jdbcTemplate.query("SELECT * FROM movie;", this::mapRowToMovie);
	}

	@Override
	public Optional<Movie> findById(long id) {
		List<Movie> results = this.jdbcTemplate.query("SELECT * FROM movie WHERE id =?",
																					this::mapRowToMovie, 
																					id);
		return results.isEmpty() ? 
				Optional.empty() : 
				Optional.of(results.get(0));
	}

	@Override
	public Movie save(Movie movie) {
		this.jdbcTemplate.update("INSERT INTO movie (name,director,year,genre,createAt,updateAt) VALUES (?,?,?,?,?,?);",
												movie.getName(),
												movie.getDirector(),
												movie.getYear(),
												movie.getGenre().toString(),
												movie.getCreateAt(),
												movie.getUpdateAt());
		return movie;
	}

	private Movie mapRowToMovie(ResultSet row, int rowNum) throws SQLException {
		Movie movie = new Movie();
		movie.setId(row.getLong("id"));
		movie.setName(row.getString("name"));
		movie.setDirector(row.getString("director"));
		movie.setYear(row.getLong("year"));
		movie.setGenre(Genre.valueOf(row.getString("genre")));

		movie.setCreateAt(row.getDate("createAt"));
		movie.setUpdateAt(row.getDate("updateAt"));
		return movie;

	}

}
