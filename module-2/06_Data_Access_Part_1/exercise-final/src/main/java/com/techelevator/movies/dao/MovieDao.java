package com.techelevator.movies.dao;

import com.techelevator.movies.model.Movie;

import java.util.List;

public interface MovieDao {

    /**
     * Get a list of all movies from the datastore.
     * The list is never null. It is empty if there are no movies in the datastore.
     *
     * @return all movies as a list of Movie objects
     */
    List<Movie> getMovies();

    /**
     * Get a movie from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param id the movie id to get from the datastore
     * @return a fully populated Movie object
     */
    Movie getMovieById(int id);

    /**
     * Get a list of all the movies from the datastore that match the title.
     * The list is never null. It is empty if no matching movies were found.
     *
     * @param title the movie title to get from the datastore
     * @param useWildCard wraps title with wild card characters if true
     * @return all matching movies as a list of Movie objects
     */
    List<Movie> getMoviesByTitle(String title, boolean useWildCard);

    /**
     * Get a list of all the movies directed by a given director and released between
     * a range of years (inclusive) from the datastore. The movies are sorted from
     * oldest to newest.
     * The list is never null. It is empty if no matching movies were found.
     *
     * @param directorName the name of the director
     * @param startYear the starting release year of the range of years
     * @param endYear the ending release year of the range of years
     * @param useWildCard wraps directorName with wild card characters if true
     * @return all matching movies as a list of Movie objects
     */
    List<Movie> getMoviesByDirectorNameAndBetweenYears(String directorName, int startYear, int endYear, boolean useWildCard);
}
