package ba.disney;

import ba.disney.character.*;
import ba.disney.thema.Adventureland;
import ba.disney.thema.Fantasyland;
import ba.disney.thema.IDisneyland;
import ba.disney.thema.MickeysToontown;
import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.image.rasterize.ShapeRasterizer;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.PictureWidget;
import org.alcibiade.asciiart.widget.TextWidget;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Adventure {
    public static void displayLands(){
        List<IDisneyland> disneyLands = new ArrayList<>();
        disneyLands.add(new Adventureland());
        disneyLands.add(new Fantasyland());
        disneyLands.add(new MickeysToontown());

        for(int i=0; i<disneyLands.size(); i++){
            System.out.println((i+1) + ". " + disneyLands.get(i).getName());
        }
    }

    public static void displayAdventureLand(){
        IDisneyland advnetureLand = new Adventureland();
        System.out.println(advnetureLand.getDescription());
    }

    public static void displayFantasyLand(){
        IDisneyland fantasyLand = new Fantasyland();
        System.out.println(fantasyLand.getDescription());
    }

    public static void displayMickeysLandOption() {
        IDisneyland mickeyLand = new MickeysToontown();
        System.out.println(mickeyLand.getDescription());

        Scanner console = new Scanner(System.in);

        displayCharacters();
        int option = console.nextInt();

        switch(option){
            case 1:
                drawCharacter(new MickeyCharacter().getImagePath());
                break;
            case 2:
                drawCharacter(new SimbaCharacter().getImagePath());
                break;
            case 3:
                drawCharacter(new OlafCharacter().getImagePath());
                break;
            case 4:
                drawCharacter(new GoofyCharacter().getImagePath());
            default:
                System.out.println("Thank You for Comming! Visit us again...");

        }

    }

    private static void drawCharacter(String imagePath) {
        ClassLoader classLoader = IDisneyland.class.getClassLoader();

        InputStream resourceAsStream = classLoader.getResourceAsStream(imagePath);
        try{
            BufferedImage image = ImageIO.read(resourceAsStream);
            TextWidget widget = new PictureWidget(new TextBoxSize(80, 30), image, new ShapeRasterizer());
            ExtensibleCharacterRaster raster = new ExtensibleCharacterRaster();

            widget.render(new RasterContext(raster));
            System.out.println(raster);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayCharacters(){
        System.out.println("We have many interesting characters in Mickeys Toontown, which one would you like to see?");
        List<DisneyCharacter> disneyCharacters = new ArrayList<>();
        disneyCharacters.add(new MickeyCharacter());
        disneyCharacters.add(new SimbaCharacter());
        disneyCharacters.add(new OlafCharacter());
        disneyCharacters.add(new GoofyCharacter());

        for(int i=0; i<disneyCharacters.size(); i++){
            System.out.println((i+1) + ". " + disneyCharacters.get(i).getName());
        }
    }
}
