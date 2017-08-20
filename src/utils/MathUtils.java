package utils;

/**
 * Created by SeventhSense on 8/20/2017.
 */
public final class MathUtils {

    private MathUtils(){}

    /*
        Rounds the input x to the nearest input specified.

        E.G roundDownToNearest(7,10) will round 7 down to the nearest 10th, in this case 0.
     */
    public static int roundDownToNearest(int x, int nearest){
        if(nearest == 0)
            throw new IllegalArgumentException("Nearest must be > 0.");
        return x - (x % nearest);
    }

    /*
       Rounds the input x to the nearest input specified.

       E.G roundUpToNearest(7,10) will round 7 up to the nearest 10th, in this case 10.
    */
    public static int roundUpToNearest(int x, int nearest){
        if(nearest == 0)
            throw new IllegalArgumentException("Nearest must be > 0.");
        return x + (nearest - ( x % nearest ));
    }
    /*
        Takes in an angle and produces a result within 0 and 360 degrees.

        Negatives will be converted to their whole counterparts whilst angles over 360 degrees will be converted
     */
    public static double normalizeAngleDegrees(double angle){
        double modAng = angle%360;
        return modAng >= 0? modAng:modAng+360;
    }
}
