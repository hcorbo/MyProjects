import ba.netflix.Genre;
import ba.netflix.Movie;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ba.disney.Adventure.*;
import static ba.netflix.MovieLibrary.*;

public class Main {

    static Logger logger = LogManager.getLogger(new Main());

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("d", "disney", true, "Display disney");
        options.addOption("n", "netflix", true, "Display netflix");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("d")) {
                System.out.println("DISNEEEEEEY");
            }
            else if (cmd.hasOption("n")){
                System.out.println("NETFLIX");
            }
            else {
            }
        } catch (ParseException e) {
            //System.out.println("Greška");
            e.printStackTrace();
        }

        System.out.println("Please enter your name: ");

        Scanner console = new Scanner(System.in);
        String name = console.next();
        logger.trace("User: " + name);

        System.out.println("\nWelcome " + name + "\n");

        System.out.println("Where would you like to go?");

        displayOption();
        int basicOption = console.nextInt();
        if(basicOption == 1) {
            displayLands();
            int option = console.nextInt();

            switch (option) {
                case 1:
                    displayAdventureLand();
                    break;
                case 2:
                    displayFantasyLand();
                    break;
                case 3:
                    displayMickeysLandOption();
                    break;
                default:
                    System.out.println("Thank You for Comming! Visit us again...");
            }
            System.out.println("\nThank You for Comming! Visit us again...\n");
            logger.trace(name + " close Disneyland app");
        } else {
            System.out.println("Choose option?");
            movieOption();
            int option = console.nextInt();
            switch (option){
                case 1:
                    displayMovies();
                    break;
                case 2:
                    System.out.println("Choose genre?");
                    displayGenres();
                    int selection = console.nextInt();
                    List<Movie> movieList =  findMovies(selection);
                    displayMovieList(movieList);
                    break;
                case 3:
                    List<Movie> sortedMovies = sortMovies();
                    displayMovieList(sortedMovies);
                    break;
                default:
                    System.out.println("Thank You for Comming! Visit us again...");
            }
            System.out.println("\nThank You for Comming! Visit us again...\n");
            logger.trace(name + " close Netflix app");
        }

    }

    public static void displayOption(){
        System.out.println("1. Disney");
        System.out.println("2. Netflix");
    }

}
