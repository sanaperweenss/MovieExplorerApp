// ✅ This class represents the structure of a movie's information
package model;

// 🎬 Movie class holds all the movie details fetched from the API
public class Movie {

    // 🔹 These are private fields (data members) for storing movie info
    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String plot;
    private String imdbRating;
    private String posterUrl;

    // ✅ Constructor to initialize a Movie object with all its details
    public Movie(String title, String year, String rated, String released, String runtime,
                 String genre, String director, String plot, String imdbRating, String posterUrl) {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.plot = plot;
        this.imdbRating = imdbRating;
        this.posterUrl = posterUrl;
    }

    // 🔍 Getter methods to safely access each property (OOP encapsulation)
    public String getTitle()       { return title; }
    public String getYear()        { return year; }
    public String getRated()       { return rated; }
    public String getReleased()    { return released; }
    public String getRuntime()     { return runtime; }
    public String getGenre()       { return genre; }
    public String getDirector()    { return director; }
    public String getPlot()        { return plot; }
    public String getImdbRating()  { return imdbRating; }
    public String getPosterUrl()   { return posterUrl; }

    // 🔁 toString() method helps in debugging — prints a nice one-line summary
    @Override
    public String toString() {
        return title + " (" + year + ") - " + genre + " | Rating: " + imdbRating;
    }
}
