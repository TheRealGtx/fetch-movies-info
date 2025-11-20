package it.unibo.fetchmovies;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static java.lang.System.out;

import org.junit.jupiter.api.Test;

import com.omertron.omdbapi.OMDBException;

/**
 * Application test.
 */
class AppTest {

    /**
     * Launches the app, intecepts exceptions.
     */
    @Test void testApp() {
        assertNotNull(System.getenv("OMDB_API_KEY"));
        assertFalse(System.getenv("OMDB_API_KEY").isBlank());
        try {
            EvenSimplerRateAMovie.main(new String[] {"Breaking Bad"});
        } catch (final OMDBException e) {
            out.println(e.getMessage());
        }
    }
}
