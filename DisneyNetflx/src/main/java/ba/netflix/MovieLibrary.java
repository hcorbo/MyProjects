package ba.netflix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MovieLibrary {
    static Logger logger = LogManager.getLogger(MovieLibrary.class);
    static List<Movie> movieList = new ArrayList<>();
    final static String PATH = "C:\\Users\\Harun\\IdeaProjects\\CodectaAcademy\\Academy\\DisneyNetflx\\src\\main\\resources\\movie.json";


    public static void readingJSON(String s){
        try {
            logger.info("Reading JSON");
            String contents = new String(Files.readAllBytes(Paths.get(s)));
            JSONObject jsonObject = new JSONObject(contents);
            JSONArray jsonArray =  jsonObject.getJSONArray("moviz");

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                Movie newMovie = new Movie();
                newMovie.setId(jsonObject1.getString("id"));
                newMovie.setTitle(jsonObject1.getString("title"));
                newMovie.setImdbRating(Double.valueOf(jsonObject1.getString("imdbRating")));


                JSONArray tempGenreArray = jsonObject1.getJSONArray("genre");

                List<Genre> temp = new ArrayList<>();
                for(int j=0; j< tempGenreArray.length(); j++){
                    temp.add(Genre.valueOf(tempGenreArray.getString(j)));
                }

                newMovie.setGenre(temp);
                movieList.add(newMovie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void movieOption(){
        System.out.println("1. List of films");
        System.out.println("2.  Genres");
        System.out.println("3. Top rated movies");
    }

    public static void displayMovies(){
        readingJSON(PATH);
        for(int i=0; i<movieList.size(); i++){
            System.out.println(movieList.get(i).getTitle());
            System.out.println("IMDB rating: " + movieList.get(i).getImdbRating());
            List<Genre> genreList = movieList.get(i).getGenre();
            for(int j=0; j<genreList.size(); j++){
                System.out.print(genreList.get(j));
                if(j != genreList.size()-1){
                    System.out.print(", ");
                }
            }
            System.out.println("\n");
        }
    }

    public static void displayMovieList(List<Movie> movieList1){
        for(Movie m : movieList1){
            System.out.println(m.getTitle());
            System.out.println("IMDB rating: " + m.getImdbRating());
            List<Genre> genreList = m.getGenre();
            for(int j=0; j<genreList.size(); j++){
                System.out.print(genreList.get(j));
                if(j != genreList.size()-1){
                    System.out.print(", ");
                }
            }
            System.out.println("\n");
        }
    }


    public static void displayGenres(){
        int i=1;
        for(Genre genre : Genre.values()){
            System.out.print(i + ". ");
            System.out.println(genre);
            i++;
        }
    }


    public static List<Movie> findMovies(int selection){
        readingJSON(PATH);
        List<Movie> movies = new ArrayList<>();
        for(Movie m : movieList){
            Genre tempGenre = Genre.values()[selection-1];
            for(Genre g: m.getGenre()){
                if(g.equals(tempGenre)){
                    movies.add(m);
                }
            }
        }
        return movies;
    }

    public static List<Movie> sortMovies(){
        readingJSON(PATH);
        Collections.sort(movieList);
        return movieList;
    }
}


