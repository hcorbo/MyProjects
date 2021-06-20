package ba.disney.thema;

public class Adventureland implements IDisneyland{
    private
    String name = "Adventureland";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Themed around tales of exotic tropical jungles in far-off lands, Aventureland is due west of Main " +
                "Street, U.S.A. and has several popular attractions.";
    }
}
