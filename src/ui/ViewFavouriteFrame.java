//package ui;
//
//import data.FavouriteManager;
//import model.Movie;
//import utils.ImageUtils;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.List;
//
//public class ViewFavouriteFrame extends JFrame {
//
//    public ViewFavouriteFrame() {
//        setTitle("⭐ Your Favorite Movies");
//        setSize(700, 500);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        List<Movie> favorites = FavouriteManager.loadFavorites();
//
//        JPanel moviePanel = new JPanel();
//        moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));
//        moviePanel.setBackground(Color.DARK_GRAY);
//
//        for (Movie movie : favorites) {
//            JPanel card = createMovieCard(movie);
//            moviePanel.add(card);
//            moviePanel.add(Box.createRigidArea(new Dimension(0, 10)));
//        }
//
//        JScrollPane scrollPane = new JScrollPane(moviePanel);
//        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//        add(scrollPane);
//        setVisible(true);
//    }
//
//    private JPanel createMovieCard(Movie movie) {
//        JPanel card = new JPanel(new BorderLayout(10, 10));
//        card.setBackground(new Color(40, 40, 40));
//        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        // 🖼 Poster image
//        JLabel posterLabel = new JLabel();
//        posterLabel.setPreferredSize(new Dimension(120, 180));
//        posterLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        posterLabel.setVerticalAlignment(SwingConstants.CENTER);
//
//        System.out.println("📦 Loading poster for: " + movie.getTitle() + " → " + movie.getPoster());
//
//        ImageIcon posterIcon = ImageUtils.fetchPoster(movie.getPoster());
//
//        if (posterIcon != null) {
//            posterLabel.setIcon(posterIcon);
//            posterLabel.setText("");
//        } else {
//            posterLabel.setText("🖼 No Image");
//            posterLabel.setForeground(Color.LIGHT_GRAY);
//        }
//
//        card.add(posterLabel, BorderLayout.WEST);
//
//        // 📋 Movie info panel
//        JPanel textPanel = new JPanel();
//        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
//        textPanel.setBackground(new Color(40, 40, 40));
//
//        JLabel titleLabel = new JLabel("🎬 " + movie.getTitle() + " (" + movie.getYear() + ")");
//        titleLabel.setForeground(Color.WHITE);
//        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
//        textPanel.add(titleLabel);
//
//        JLabel ratingLabel = new JLabel("⭐ IMDb: " + movie.getRating());
//        ratingLabel.setForeground(Color.ORANGE);
//        textPanel.add(ratingLabel);
//
//        JLabel genreLabel = new JLabel("🎭 Genre: " + movie.getGenre());
//        genreLabel.setForeground(Color.LIGHT_GRAY);
//        textPanel.add(genreLabel);
//
//        JTextArea plotArea = new JTextArea(movie.getPlot());
//        plotArea.setLineWrap(true);
//        plotArea.setWrapStyleWord(true);
//        plotArea.setEditable(false);
//        plotArea.setOpaque(false);
//        plotArea.setForeground(Color.WHITE);
//        plotArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
//        plotArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
//        textPanel.add(plotArea);
//
//        // 🗑 Remove button
//        JButton removeButton = new JButton("🗑 Remove");
//        removeButton.setForeground(Color.WHITE);
//        removeButton.setBackground(new Color(120, 30, 30));
//        removeButton.setFocusPainted(false);
//        removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
//
//        removeButton.addActionListener(e -> {
//            int confirm = JOptionPane.showConfirmDialog(
//                    this,
//                    "Are you sure you want to remove \"" + movie.getTitle() + "\"?",
//                    "Confirm Delete",
//                    JOptionPane.YES_NO_OPTION
//            );
//
//            if (confirm == JOptionPane.YES_OPTION) {
//                FavouriteManager.removeFromFavorites(movie);
//                dispose();              // close the old window
//                new ViewFavouriteFrame(); // open updated window
//            }
//        });
//
//        textPanel.add(Box.createVerticalStrut(10));
//        textPanel.add(removeButton);
//
//        card.add(textPanel, BorderLayout.CENTER);
//        return card;
//    }
//}
//


package ui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import data.FavouriteManager;
import model.Movie;
import utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewFavouriteFrame extends JFrame {

    public ViewFavouriteFrame() {
        setTitle("⭐ Your Favorite Movies");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🌓 Top panel with theme toggle
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.DARK_GRAY);

        JToggleButton themeToggle = new JToggleButton("🌙 Dark Mode");
        themeToggle.setFocusPainted(false);
        themeToggle.setBackground(new Color(70, 70, 70));
        themeToggle.setForeground(Color.WHITE);

        // 🔄 Theme switch logic
        themeToggle.addActionListener(e -> {
            try {
                if (themeToggle.isSelected()) {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    themeToggle.setText("🌞 Light Mode");
                } else {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    themeToggle.setText("🌙 Dark Mode");
                }

                // 🔄 Refresh all components to reflect new theme
                SwingUtilities.updateComponentTreeUI(this);

            } catch (Exception ex) {
                System.out.println("❌ Theme switch failed: " + ex.getMessage());
            }
        });

        topPanel.add(themeToggle);
        add(topPanel, BorderLayout.NORTH);

        // 🔃 Load all saved favorites
        List<Movie> favorites = FavouriteManager.loadFavorites();

        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));
        moviePanel.setBackground(Color.DARK_GRAY);

        for (Movie movie : favorites) {
            JPanel card = createMovieCard(movie);
            moviePanel.add(card);
            moviePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JScrollPane scrollPane = new JScrollPane(moviePanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createMovieCard(Movie movie) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(new Color(40, 40, 40));
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 🖼 Poster image
        JLabel posterLabel = new JLabel();
        posterLabel.setPreferredSize(new Dimension(120, 180));
        posterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        posterLabel.setVerticalAlignment(SwingConstants.CENTER);

        System.out.println("📦 Loading poster for: " + movie.getTitle() + " → " + movie.getPoster());

        ImageIcon posterIcon = ImageUtils.fetchPoster(movie.getPoster());

        if (posterIcon != null) {
            posterLabel.setIcon(posterIcon);
            posterLabel.setText("");
        } else {
            posterLabel.setText("🖼 No Image");
            posterLabel.setForeground(Color.LIGHT_GRAY);
        }

        card.add(posterLabel, BorderLayout.WEST);

        // 📋 Movie info panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("🎬 " + movie.getTitle() + " (" + movie.getYear() + ")");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        textPanel.add(titleLabel);

        JLabel ratingLabel = new JLabel("⭐ IMDb: " + movie.getRating());
        ratingLabel.setForeground(Color.ORANGE);
        textPanel.add(ratingLabel);

        JLabel genreLabel = new JLabel("🎭 Genre: " + movie.getGenre());
        genreLabel.setForeground(Color.LIGHT_GRAY);
        textPanel.add(genreLabel);

        JTextArea plotArea = new JTextArea(movie.getPlot());
        plotArea.setLineWrap(true);
        plotArea.setWrapStyleWord(true);
        plotArea.setEditable(false);
        plotArea.setOpaque(false);
        plotArea.setForeground(Color.WHITE);
        plotArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        plotArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        textPanel.add(plotArea);

        // 🗑 Remove button
        JButton removeButton = new JButton("🗑 Remove");
        removeButton.setForeground(Color.WHITE);
        removeButton.setBackground(new Color(120, 30, 30));
        removeButton.setFocusPainted(false);
        removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        removeButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to remove \"" + movie.getTitle() + "\"?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                FavouriteManager.removeFromFavorites(movie);
                dispose();              // close the old window
                new ViewFavouriteFrame(); // open updated window
            }
        });

        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(removeButton);

        card.add(textPanel, BorderLayout.CENTER);
        return card;
    }
}

