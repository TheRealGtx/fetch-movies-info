package it.unibo.fetchmovies;

import com.omertron.omdbapi.OMDBException;
import com.omertron.omdbapi.OmdbApi;
import com.omertron.omdbapi.tools.OmdbBuilder;
import java.util.LinkedList;
import java.util.List;

/**
 * Monolitic application that fetches movie rates.
 */
public final class SimplerRateAMovie {
    private static final String DEFAULT_MOVIE = "Breaking Bad";
    private static final String OMDB_API_KEY = System.getenv("OMDB_API_KEY");

    /**
     * Returns a string with the desired infos.
     * 
     * @param movieNames the movies wich infos are to retrieve
     * @return a string with the infos of all the movies
     * @throws OMDBException exception determined by the OMBD apis
     */
    public String getMovieInfo(final String[] movieNames) throws OMDBException {
        if (OMDB_API_KEY == null || OMDB_API_KEY.isBlank()) {
            return "Invalid OMDB API Key {}, please set a valid API Key as the environment variable OMDB_API_KEY";
        }

        final String[] titles = movieNames.length == 0 ? new String[] {DEFAULT_MOVIE} : movieNames;
        final OmdbApi omdb = new OmdbApi(OMDB_API_KEY);
        final List<String> results = new LinkedList<>();

        for (final var title: titles) {
            final var searchResults = omdb.search(title).getResults();

            for (final var searchResult: searchResults) {
                final var movieHandler = new OmdbBuilder().setApiKey(OMDB_API_KEY)
                    .setPlotFull().setImdbId(searchResult.getImdbID()).build();
                final var movie = omdb.getInfo(movieHandler);

                results.add("\n=========================\n" + movie.getTitle() + "\nDirected by " + movie.getDirector()
                + "; " + String.join(", ", movie.getCountries()) + "; " + movie.getYear()
                + ".\nWritten by " + movie.getWriter() + ".\nCast: " + movie.getActors() + ".\n"
                + movie.getRuntime() + "; " + movie.getGenre() + ".\nIMDb " + movie.getImdbRating()
                + " (over " + movie.getImdbVotes() + " votes). Awards: " + movie.getAwards() + ".\n"
                + movie.getPlot());
            }
        }

        return results.toString();
    }
}
