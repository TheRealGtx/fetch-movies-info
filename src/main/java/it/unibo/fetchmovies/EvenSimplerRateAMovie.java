package it.unibo.fetchmovies;

import com.omertron.omdbapi.OMDBException;

/**
 * Monolitic application that fetches movie rates.
 */
public final class EvenSimplerRateAMovie { 
    private EvenSimplerRateAMovie() { }

    /**
     * Launches the application.
     * 
     * @param args a string with the movie/series name.
     */
    public static void main(final String[] args) throws OMDBException {
        System.out.println(new SimplerRateAMovie().getMovieInfo(args)); //NOPMD I need to print the results
    }
}
