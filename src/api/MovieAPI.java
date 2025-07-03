// 📦 This class is part of the 'api' package
package api;

// 👇 We're using our custom Movie model to store the API response data
import model.Movie;

// 👇 These imports are for making network requests and parsing JSON
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieAPI {

    // 🔐 This is your personal OMDb API key (replace it if needed)
    private static final String API_KEY = "a655814d";

    /**
     * 🔍 This method fetches movie info from OMDb API by title
     * @param title - the movie name you want to search for
     * @return a Movie object with title, year, rating, genre, plot, and poster if found; otherwise null
     */
    public static Movie searchMovie(String title) {
        try {
            // ✅ Step 1: Replace spaces with '+' to make the title URL-safe
            String query = title.replace(" ", "+");

            // ✅ Step 2: Build the full URL for the OMDb API call
            URL url = new URL("https://www.omdbapi.com/?t=" + query + "&apikey=" + API_KEY);

            // ✅ Step 3: Open an HTTP connection to the API
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // We are sending a GET request

            // ✅ Step 4: Read the response line by line using BufferedReader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())  // Converts bytes to characters
            );

            // ✅ Step 5: Build a full JSON string from all response lines
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line); // Append each line of response
            }
            reader.close(); // Always close your reader to free resources

            System.out.println("📦 Raw API Response:\n" + jsonBuilder.toString());

            // ✅ Step 6: Parse the JSON response
            JSONObject json = new JSONObject(jsonBuilder.toString());

            // ✅ Step 7: Check if movie was found ("Response": "True")
            if (json.has("Response") && json.getString("Response").equalsIgnoreCase("True")) {

                // ✅ Step 8: Extract required movie data
                String titleVal = json.getString("Title");
                String year = json.getString("Year");
                String rating = json.getString("imdbRating");
                String genre = json.getString("Genre");
                String plot = json.getString("Plot");
                String posterUrl = json.getString("Poster"); // 🖼 Poster image URL

                // ✅ Step 9: Return a Movie object populated with data
                return new Movie(titleVal, year, rating, genre, plot, posterUrl);

            } else {
                // ❌ If movie not found, return null
                System.out.println("⚠️ Movie not found: " + title);
                return null;
            }

        } catch (Exception e) {
            // ⚠️ If any error occurs (like API down or internet issue)
            System.out.println("❌ Error fetching movie: " + e.getMessage());
            return null;
        }
    }
}
