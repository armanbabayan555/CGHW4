package CGHW4;

public class Ray {

    private final float[] pos;
    private final float[] dir;

    public Ray(float[] pos, float[] dir) {
        this.pos = pos;
        this.dir = dir;
    }

    public float[] getPos() {
        return pos;
    }

    public float[] getDir() {
        return dir;
    }
}
