package ba.netflix;


import java.util.List;

public class Movie implements Comparable<Movie> {
    String id;
    String title;
    List<Genre> genre;
    Double imdbRating;

    public Movie(String id, String title, List<Genre> genre, Double imdbRating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.imdbRating = imdbRating;
    }

    public Movie() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int compareTo(Movie m) {
        if(this.imdbRating>m.imdbRating) return -1;
        if(this.imdbRating<m.imdbRating) return 1;
        return 0;
    }
}
