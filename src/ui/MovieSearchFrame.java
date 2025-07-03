//package ui;
//
//import api.MovieAPI;
//import data.FavouriteManager;
//import model.Movie;
//import utils.ImageUtils;
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//
///**
// * 🎬 Movie Search Frame — The main GUI to search movies, show details, save to favorites, and view favorites.
// */
//public class MovieSearchFrame extends JFrame {
//
//    // ✅ Declare all UI components
//    private JTextField searchField;
//    private JButton searchButton;
//    private JButton saveButton;
//    private JButton viewFavoritesButton;
//
//    private JLabel titleLabel, yearLabel, ratingLabel, genreLabel, plotLabel, posterLabel;
//
//    private JPanel detailPanel; // panel for info
//    private JPanel posterPanel; // panel for poster
//
//    private Movie currentMovie; // to hold the last fetched movie
//
//    public MovieSearchFrame() {
//        setTitle("🎥 Movie Explorer"); // Window title
//        setSize(800, 500); // Window size
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout()); // Use BorderLayout for better control
//
//        // 🖼 FlatLaf Theme
//        try {
//            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
//        } catch (Exception e) {
//            System.out.println("❌ Theme error: " + e.getMessage());
//        }
//
//        // 🔍 Top Search Panel (NORTH)
//        JPanel topPanel = new JPanel();
//        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centered with padding
//
//        searchField = new JTextField(30); // Wide input
//        searchButton = new JButton("🔍 Search");
//        saveButton = new JButton("💾 Save to Favorites");
//        viewFavoritesButton = new JButton("📂 View Favorites");
//
//        // Add input + buttons to the top panel
//        topPanel.add(searchField);
//        topPanel.add(searchButton);
//        topPanel.add(saveButton);
//        topPanel.add(viewFavoritesButton);
//
//        // 🎞 Poster Panel (WEST)
//        posterPanel = new JPanel();
//        posterPanel.setPreferredSize(new Dimension(200, 300)); // fixed width
//        posterLabel = new JLabel(); // Empty initially
//        posterPanel.add(posterLabel);
//
//        // 📝 Detail Panel (CENTER)
//        detailPanel = new JPanel();
//        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS)); // Vertical layout
//
//        titleLabel = createStyledLabel("");
//        yearLabel = createStyledLabel("");
//        ratingLabel = createStyledLabel("");
//        genreLabel = createStyledLabel("");
//        plotLabel = createStyledLabel("<html></html>");
//
//        // Add info labels to the panel
//        detailPanel.add(titleLabel);
//        detailPanel.add(yearLabel);
//        detailPanel.add(ratingLabel);
//        detailPanel.add(genreLabel);
//        detailPanel.add(plotLabel);
//
//        // 🔗 Add top, left, and center panels to the frame
//        add(topPanel, BorderLayout.NORTH);
//        add(posterPanel, BorderLayout.WEST);
//        add(detailPanel, BorderLayout.CENTER);
//
//        // 🧠 Button Logic
//        searchButton.addActionListener(this::handleSearch); // When Search is clicked
//        saveButton.addActionListener(e -> handleSave());    // When Save is clicked
//        viewFavoritesButton.addActionListener(e -> new ViewFavouriteFrame()); // Show saved list
//
//        setVisible(true); // 🎉 Show the UI
//    }
//
//    /**
//     * 🔍 Handles search logic when Search button is clicked
//     */
//    private void handleSearch(ActionEvent e) {
//        String title = searchField.getText();
//        currentMovie = MovieAPI.searchMovie(title); // Call the API
//
//        if (currentMovie != null) {
//            // ✅ Populate text labels with info
//            titleLabel.setText("🎬 Title: " + currentMovie.getTitle());
//            yearLabel.setText("📅 Year: " + currentMovie.getYear());
//            ratingLabel.setText("⭐ IMDb Rating: " + currentMovie.getRating());
//            genreLabel.setText("🎭 Genre: " + currentMovie.getGenre());
//            plotLabel.setText("<html><body style='width: 400px'>" + currentMovie.getPlot() + "</body></html>");
//
//            // 🖼 Fetch and display poster
//            posterLabel.setIcon(ImageUtils.fetchPoster(currentMovie.getPoster()));
//
//        } else {
//            JOptionPane.showMessageDialog(this, "❌ Movie not found.");
//        }
//    }
//
//    /**
//     * 💾 Save the current movie to favorite list
//     */
//    private void handleSave() {
//        if (currentMovie != null) {
//            FavouriteManager.saveToFile(currentMovie); // Save using data layer
//            JOptionPane.showMessageDialog(this, "✅ Saved to favorites!");
//        }
//    }
//
//    /**
//     * 🎨 Custom styled label generator
//     */
//    private JLabel createStyledLabel(String text) {
//        JLabel label = new JLabel(text);
//        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//        label.setForeground(Color.WHITE); // for dark background
//        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // spacing
//        return label;
//    }
//}


package ui;

import api.MovieAPI;
import data.FavouriteManager;
import model.Movie;
import utils.ImageUtils;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 🎬 Movie Search Frame — The main GUI to search movies, show details, save to favorites, and view favorites.
 */
public class MovieSearchFrame extends JFrame {

    // ✅ Declare all UI components
    private JTextField searchField;
    private JButton searchButton;
    private JButton saveButton;
    private JButton viewFavoritesButton;
    private JToggleButton themeToggle;

    private JLabel titleLabel, yearLabel, ratingLabel, genreLabel, plotLabel, posterLabel;

    private JPanel detailPanel; // panel for info
    private JPanel posterPanel; // panel for poster

    private Movie currentMovie; // to hold the last fetched movie

    public MovieSearchFrame() {
        setTitle("🎥 Movie Explorer");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🖼 Default to Dark Theme
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println("❌ Theme error: " + e.getMessage());
        }

        // 🔍 Top Search Panel + Theme Toggle (NORTH)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(Color.DARK_GRAY);

        searchField = new JTextField(30);
        searchButton = new JButton("🔍 Search");
        saveButton = new JButton("💾 Save to Favorites");
        viewFavoritesButton = new JButton("📂 View Favorites");

        // 🌗 Theme toggle button
        themeToggle = new JToggleButton("🌙 Dark Mode");
        themeToggle.setFocusPainted(false);
        themeToggle.setBackground(new Color(70, 70, 70));
        themeToggle.setForeground(Color.WHITE);

        themeToggle.addActionListener(e -> {
            try {
                if (themeToggle.isSelected()) {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    themeToggle.setText("☀️ Light Mode");
                } else {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    themeToggle.setText("🌙 Dark Mode");
                }
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ex) {
                System.out.println("❌ Theme switch failed: " + ex.getMessage());
            }
        });

        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(saveButton);
        topPanel.add(viewFavoritesButton);
        topPanel.add(themeToggle);
        add(topPanel, BorderLayout.NORTH);

        // 🎞 Poster Panel (WEST)
        posterPanel = new JPanel();
        posterPanel.setPreferredSize(new Dimension(200, 300));
        posterLabel = new JLabel();
        posterPanel.add(posterLabel);
        add(posterPanel, BorderLayout.WEST);

        // 📝 Detail Panel (CENTER)
        detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setBackground(Color.DARK_GRAY);

        titleLabel = createStyledLabel("");
        yearLabel = createStyledLabel("");
        ratingLabel = createStyledLabel("");
        genreLabel = createStyledLabel("");
        plotLabel = createStyledLabel("<html></html>");

        detailPanel.add(titleLabel);
        detailPanel.add(yearLabel);
        detailPanel.add(ratingLabel);
        detailPanel.add(genreLabel);
        detailPanel.add(plotLabel);
        add(detailPanel, BorderLayout.CENTER);

        // 🧠 Button Logic
        searchButton.addActionListener(this::handleSearch);
        saveButton.addActionListener(e -> handleSave());
        viewFavoritesButton.addActionListener(e -> new ViewFavouriteFrame());

        setVisible(true);
    }

    /**
     * 🔍 Search for movie from API
     */
    private void handleSearch(ActionEvent e) {
        String title = searchField.getText();
        currentMovie = MovieAPI.searchMovie(title);

        if (currentMovie != null) {
            titleLabel.setText("🎬 Title: " + currentMovie.getTitle());
            yearLabel.setText("📅 Year: " + currentMovie.getYear());
            ratingLabel.setText("⭐ IMDb Rating: " + currentMovie.getRating());
            genreLabel.setText("🎭 Genre: " + currentMovie.getGenre());
            plotLabel.setText("<html><body style='width: 400px'>" + currentMovie.getPlot() + "</body></html>");

            posterLabel.setIcon(ImageUtils.fetchPoster(currentMovie.getPoster()));
        } else {
            JOptionPane.showMessageDialog(this, "❌ Movie not found.");
        }
    }

    /**
     * 💾 Save the current movie to favorites
     */
    private void handleSave() {
        if (currentMovie != null) {
            FavouriteManager.saveToFile(currentMovie);
            JOptionPane.showMessageDialog(this, "✅ Saved to favorites!");
        }
    }

    /**
     * 🎨 Styled label generator
     */
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return label;
    }
}
