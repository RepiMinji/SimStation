package simStation;
import java.util.Random;

public enum Heading {
    NORTH, EAST, SOUTH, WEST, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST;
    public static Heading random()
    {
        Random r = new Random();
        int n = r.nextInt(4);
        if(n == 0)
        {
            return Heading.NORTH;
        }
        else if(n == 1)
        {
            return Heading.EAST;
        }
        else if(n == 2)
        {
            return Heading.SOUTH;
        }
        else if(n == 3)
        {
            return Heading.WEST;
        }

        else if(n == 4)
        {
            return Heading.NORTHWEST;
        }
        else if(n == 5)
        {
            return Heading.NORTHEAST;
        }
        else if(n == 6)
        {
            return Heading.SOUTHWEST;
        }
        else if(n == 7)
        {
            return Heading.SOUTHEAST;
        }
        return null;
    }
}
