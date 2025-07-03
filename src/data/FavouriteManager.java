package data;

import model.Movie;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 💾 FavouriteManager — Handles saving and loading of user's favorite movies
 * from a local text file (`favorites.txt`) in a structured way.
 */
public class FavouriteManager {

    private static final String FILENAME = "resources/favorites.txt";

    // Used to split/join movie fields safely
    private static final String DELIMITER = "\\|";
    private static final String DELIMITER_WRITE = "|";

    // Escape any pipe symbols (|) from user input
    private static String escape(String input) {
        return input.replace("|", "##pipe##");
    }

    // Convert escaped strings back to normal
    private static String unescape(String input) {
        return input.replace("##pipe##", "|");
    }

    /**
     * 💾 Save a Movie to favorites file
     */
    public static void saveToFile(Movie movie) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            writer.write(escape(movie.getTitle()) + DELIMITER_WRITE +
                    escape(movie.getYear()) + DELIMITER_WRITE +
                    escape(movie.getRating()) + DELIMITER_WRITE +
                    escape(movie.getGenre()) + DELIMITER_WRITE +
                    escape(movie.getPlot()) + DELIMITER_WRITE +
                    escape(movie.getPoster()));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("❌ Failed to save movie: " + e.getMessage());
        }
    }

    /**
     * 📂 Load favorites from file
     */
    public static List<Movie> loadFavorites() {
        List<Movie> favorites = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(FILENAME));

            for (String line : lines) {
                String[] parts = line.split(DELIMITER, 6);
                if (parts.length == 6) {
                    Movie movie = new Movie(
                            unescape(parts[0]),
                            unescape(parts[1]),
                            unescape(parts[2]),
                            unescape(parts[3]),
                            unescape(parts[4]),
                            unescape(parts[5])
                    );
                    favorites.add(movie);
                } else {
                    System.out.println("⚠️ Skipped malformed favorite: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("❌ Failed to load favorites: " + e.getMessage());
        }

        return favorites;
    }

    /**
     * 🗑 Remove a movie from the favorites list by title and year.
     * Rewrites the file without the selected movie.
     */
    public static void removeFromFavorites(Movie toRemove) {
        List<Movie> current = loadFavorites();       // Get all saved favorites
        List<Movie> updated = new ArrayList<>();     // New list without deleted movie

        for (Movie m : current) {
            // Keep only movies that are NOT the one to delete (based on title + year)
            if (!m.getTitle().equals(toRemove.getTitle()) || !m.getYear().equals(toRemove.getYear())) {
                updated.add(m);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Movie m : updated) {
                writer.write(escape(m.getTitle()) + DELIMITER_WRITE +
                        escape(m.getYear()) + DELIMITER_WRITE +
                        escape(m.getRating()) + DELIMITER_WRITE +
                        escape(m.getGenre()) + DELIMITER_WRITE +
                        escape(m.getPlot()) + DELIMITER_WRITE +
                        escape(m.getPoster()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Failed to update favorites file: " + e.getMessage());
        }
    }
}
