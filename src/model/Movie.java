package model; // This class is part of the 'model' package

/**
 * 📦 This class represents a Movie object.
 * It stores basic details about a movie fetched from the OMDb API.
 */

/**
 * 🎬 Movie class holds details of one movie.
 */
public class Movie {
    private String title;
    private String year;
    private String rating;
    private String genre;
    private String plot;
    private String poster; // ✅ Poster URL field

    // ✅ Constructor with all 6 fields
    public Movie(String title, String year, String rating, String genre, String plot, String poster) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
        this.plot = plot;
        this.poster = poster;
    }

    // ✅ Getters
    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getRating() { return rating; }
    public String getGenre() { return genre; }
    public String getPlot() { return plot; }
    public String getPoster() { return poster; } // ✅ Use this to display poster in GUI
}
