package utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * 🖼 ImageUtils — Fetches and resizes movie poster images for GUI display.
 */
public class ImageUtils {

    // ✅ Standard thumbnail size used across the app (modify if needed)
    private static final int POSTER_WIDTH = 120;   // width in pixels
    private static final int POSTER_HEIGHT = 180;  // height in pixels

    /**
     * 🔍 Fetches a movie poster from a given URL and returns it as a scaled ImageIcon.
     *
     * @param posterUrl The URL of the movie poster (from OMDb API)
     * @return Scaled ImageIcon, or null if image failed to load
     */
    public static ImageIcon fetchPoster(String posterUrl) {
        try {
            // ❌ Sanity check: make sure we got a usable URL
            if (posterUrl == null || posterUrl.trim().isEmpty() ||
                    posterUrl.equalsIgnoreCase("N/A") ||
                    !posterUrl.startsWith("http")) {
                System.out.println("⚠️ No valid poster URL provided: " + posterUrl);
                return null;
            }

            // 🌐 Create a URL object and attempt to download the image
            URL url = new URL(posterUrl);
            Image image = ImageIO.read(url); // returns null if invalid or timed out

            // ✅ Successfully loaded — now scale it to standard size
            if (image != null) {
                Image scaled = image.getScaledInstance(
                        POSTER_WIDTH,
                        POSTER_HEIGHT,
                        Image.SCALE_SMOOTH
                );
                return new ImageIcon(scaled);
            } else {
                System.out.println("⚠️ ImageIO returned null for URL: " + posterUrl);
                return null;
            }

        } catch (IOException e) {
            System.out.println("❌ Failed to load image from URL: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("❌ Unexpected error while fetching poster: " + e.getMessage());
            return null;
        }
    }
}
