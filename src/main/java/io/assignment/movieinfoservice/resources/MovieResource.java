package io.assignment.movieinfoservice.resources;

import io.assignment.movieinfoservice.models.Movie;
import io.assignment.movieinfoservice.models.MovieSummary;
import io.assignment.movieinfoservice.repository.MovieRepository;
import io.assignment.movieinfoservice.repository.MovieSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieSummaryRepository movieSummaryRepository;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.orElseThrow(() -> new NoSuchElementException());

    }

    @PostMapping("/movie")
    public Movie setRatings(@RequestBody Movie movie) {
        movie = movieRepository.saveAndFlush(movie);
        return movie;
    }

    @PostMapping("/movieSummary/{movieId}")
    public MovieSummary setRatings(@RequestBody MovieSummary movieSummary, @PathVariable("movieId") Long movieId) {
        Optional<Movie> movie= movieRepository.findById(movieId);
        movieSummary.setMovie(movie.orElseThrow(() -> new NoSuchElementException()));
        movieSummary = movieSummaryRepository.saveAndFlush(movieSummary);
        return movieSummary;
    }

    @GetMapping("/movieSummary/{movieId}")
    public List<MovieSummary> getMovieSummaries(@PathVariable("movieId") Long movieId) {
        Movie movie= movieRepository.findById(movieId).orElseThrow(() -> new NoSuchElementException());
        List<MovieSummary> summaries = movieSummaryRepository.getByMovieId(movie.getId());
        return summaries;
    }


}
