package math;

import utils.MathUtils;

import java.util.Objects;

/**
 * Created by SeventhSense on 8/18/2017.
 */
public class Vector2D {

    private double x;
    private double y;

    /**
     * Instantiates a new Vector 2 d.
     *
     * @param x the x
     * @param y the y
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new Vector 2 d.
     */
    public Vector2D() {
        this(0, 0);
    }

    /**
     * Sub x vector 2 d.
     *
     * @param x the x
     * @return the vector 2 d
     */
    public Vector2D subX(int x) {
        return addX(-x);
    }

    /**
     * Sub y vector 2 d.
     *
     * @param y the y
     * @return the vector 2 d
     */
    public Vector2D subY(int y) {
        return addX(-y);
    }

    /**
     * Sub xy vector 2 d.
     *
     * @param x the x
     * @return the vector 2 d
     */
    public Vector2D subXY(int x) {
        return subX(x).subY(x);
    }

    /**
     * Sub vector 2 d.
     *
     * @param x the x
     * @param y the y
     * @return the vector 2 d
     */
    public Vector2D sub(double x, double y) {
        return addX(-x).addY(-y);
    }

    /**
     * Sub vector 2 d.
     *
     * @param vec the vec
     * @return the vector 2 d
     */
    public Vector2D sub(Vector2D vec) {
        return sub(vec.x, vec.y);
    }

    /**
     * Add vector 2 d.
     *
     * @param x the x
     * @param y the y
     * @return the vector 2 d
     */
    public Vector2D add(double x, double y) {
        return addX(x).addY(y);
    }

    /**
     * Add x vector 2 d.
     *
     * @param x the x
     * @return the vector 2 d
     */
    public Vector2D addX(double x) {
        this.x += x;
        return this;
    }

    /**
     * Add y vector 2 d.
     *
     * @param y the y
     * @return the vector 2 d
     */
    public Vector2D addY(double y) {
        this.y += y;
        return this;
    }

    /**
     * Div x vector 2 d.
     *
     * @param x the x
     * @return the vector 2 d
     */
    public Vector2D divX(double x) {
        if (x == 0) throw new RuntimeException("Div by 0");
        return multX(1 / x);
    }

    /**
     * Div y vector 2 d.
     *
     * @param y the y
     * @return the vector 2 d
     */
    public Vector2D divY(double y) {
        if (y == 0) throw new RuntimeException("Div by 0");
        return multY(1 / y);
    }

    /**
     * Div xy vector 2 d.
     *
     * @param x the x
     * @return the vector 2 d
     */
    public Vector2D divXY(double x) {
        return divX(x).divY(x);
    }

    /**
     * Div vector 2 d.
     *
     * @param x the x
     * @param y the y
     * @return the vector 2 d
     */
    public Vector2D div(double x, double y) {
        return divX(x).divY(y);
    }

    /**
     * Div vector 2 d.
     *
     * @param vec the vec
     * @return the vector 2 d
     */
    public Vector2D div(Vector2D vec) {
        return div(vec.x, vec.y);
    }

    /**
     * Mult x vector 2 d.
     *
     * @param scale the scale
     * @return the vector 2 d
     */
    public Vector2D multX(double scale) {
        this.x *= scale;
        return this;
    }

    /**
     * Mult y vector 2 d.
     *
     * @param scale the scale
     * @return the vector 2 d
     */
    public Vector2D multY(double scale) {
        this.y *= scale;
        return this;
    }

    /**
     * Mul vector 2 d.
     *
     * @param x the x
     * @param y the y
     * @return the vector 2 d
     */
    public Vector2D mul(double x, double y) {
        return multX(x).multY(y);
    }

    /**
     * Mult xy vector 2 d.
     *
     * @param scale the scale
     * @return the vector 2 d
     */
    public Vector2D multXY(double scale) {
        return this.multX(scale).multY(scale);
    }

    /**
     * Mult vector 2 d.
     *
     * @param vector the vector
     * @return the vector 2 d
     */
    public Vector2D mult(Vector2D vector) {
        return mul(vector.x, vector.y);
    }

    /**
     * Copy vector 2 d.
     *
     * @return the vector 2 d
     */
    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }


    /**
     * Add vector 2 d.
     *
     * @param vec the vec
     * @return the vector 2 d
     */
    public Vector2D add(Vector2D vec) {
        return this.add(vec.x, vec.y);
    }

    /**
     * Magnitude squared double.
     *
     * @return the double
     */
    public double magnitudeSquared() {
        return Math.pow(x, 2) + Math.pow(y, 2);
    }

    /**
     * Magnitude double.
     *
     * @return the double
     */
    public double magnitude() {
        return Math.sqrt(magnitudeSquared());
    }

    /**
     * Angle radians double.
     *
     * @return the double
     */
//pi r = 180
    public double angleRadians() {
        return Math.atan2(this.y, this.x);
    }

    /**
     * Gets angle degrees.
     *
     * @return the angle degrees
     */
    public double getAngleDegrees() {
        return angleRadians() * (180 / Math.PI);
    }

    /**
     * Sets angle degrees.
     *
     * @param angle the angle
     * @return the angle degrees
     */
//cos x = a/h
    public Vector2D setAngleDegrees(double angle) {
        double angleR = (Math.PI / 180) * angle;
        return setAngleRadians(angleR);
    }

    /**
     * Gets angle degrees normalized.
     *
     * @return the angle degrees normalized
     */
    public double getAngleDegreesNormalized() {
        return MathUtils.normalizeAngleDegrees(getAngleDegrees());
    }

    /**
     * Mut vector 2 d.
     *
     * @param angleRadians the angle radians
     * @param length       the length
     * @return the vector 2 d
     */
    public Vector2D mut(double angleRadians, double length) {
        this.x = Math.cos(angleRadians) * length;
        this.y = Math.sin(angleRadians) * length;
        return this;
    }

    /**
     * Returns |A||B|cos(theta) if vectors are not normalized
     * otherwise return
     *
     * @param vec the vec
     * @return the double
     */
    public double dot(Vector2D vec) {
        return (this.x * vec.x) + (this.y + vec.y);
    }

    /**
     * Creates a copy of both vectors and normalizes them and returns the dot product.
     * <p>
     * This will not norm the underlying vector this operation is being performed upon due to copies being created.
     * <p>
     * Returns Cos(theta)
     *
     * @param vec the vec
     * @return the double
     */
    public double dotNormalize(Vector2D vec) {
        Vector2D thisCopy = this.copy();
        Vector2D thatCopy = vec.copy();

        return thisCopy.norm().dot(thatCopy.norm());
    }

    /**
     * Dot normalize radians double.
     *
     * @param vec the vec
     * @return the double
     */
    public double dotNormalizeRadians(Vector2D vec) {
        return Math.acos(dotNormalize(vec));
    }

    /**
     * Dot normalize degrees double.
     *
     * @param vec the vec
     * @return the double
     */
    public double dotNormalizeDegrees(Vector2D vec) {
        return dotNormalizeRadians(vec) * (180 / Math.PI);
    }

    /**
     * Sets angle radians.
     *
     * @param angle the angle
     * @return the angle radians
     */
    public Vector2D setAngleRadians(double angle) {
        return mut(angle, magnitude());
    }

    /**
     * Norm vector 2 d.
     *
     * @return the vector 2 d
     */
    public Vector2D norm() {
        this.x /= magnitude();
        this.y /= magnitude();
        return this;
    }

    /**
     * Sets magnitude.
     *
     * @param length the length
     * @return the magnitude
     */
    public Vector2D setMagnitude(double length) {
        return mut(angleRadians(), length);
    }

    /**
     * Become vector 2 d.
     *
     * @param v the v
     * @return the vector 2 d
     */
    public Vector2D become(Vector2D v) {
        return setX(v.x).setY(v.y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     * @return the x
     */
    public Vector2D setX(double x) {
        this.x = x;
        return this;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     * @return the y
     */
    public Vector2D setY(double y) {
        this.y = y;
        return this;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == null || !(obj instanceof Vector2D)) return false;

        Vector2D v2d = (Vector2D) obj;


        return v2d.x == this.x && v2d.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x,this.y);
    }
}
