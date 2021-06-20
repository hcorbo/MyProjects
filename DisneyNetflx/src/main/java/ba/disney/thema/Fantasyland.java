package ba.disney.thema;

public class Fantasyland implements IDisneyland {
    String name = "Fantasyland";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Fantasyland is the area of Disneyland of which Walt Disney said, \"What youngster has not dreamed of flying " +
                "with Peter Pan over moonlit London, or tumbling into Alice\'s nonsensical Wonderland? In Fantasyland, these classic" +
                " stories of everyone\'s youth have become realities for youngsters – of all ages – to participate in.";
    }
}
