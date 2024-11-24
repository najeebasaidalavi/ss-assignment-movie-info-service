package io.assignment.movieinfoservice.repository;

import io.assignment.movieinfoservice.models.MovieSummary;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieSummaryRepository extends JpaRepository<MovieSummary, Long>{

    @Query("Select MS from MovieSummary MS where MS.movie.id = :movieId")
    List<MovieSummary> getByMovieId(Long movieId);
}
