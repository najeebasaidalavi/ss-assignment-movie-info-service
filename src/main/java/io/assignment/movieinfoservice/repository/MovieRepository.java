package io.assignment.movieinfoservice.repository;

import io.assignment.movieinfoservice.models.Movie;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
