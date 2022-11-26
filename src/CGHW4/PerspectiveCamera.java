package CGHW4;

import static java.lang.Math.*;

public class PerspectiveCamera {

    public Ray constructRayThroughPixel(int i, int j) {
        float NDCx = (i + 0.5f) / Images.getWidth();
        float NDCy = (j + 0.5f) / Images.getHeight();

        float screenX = 2 * NDCx - 1;
        float screenY = 1 - 2 * NDCy;

        float thetaOverTwo = (float) tan(toRadians(100) / 2);
        float cameraX = thetaOverTwo * (2 * screenX - 1);
        float cameraY = thetaOverTwo * (1 - 2.0f * screenY);

        return new Ray(new float[]{0, 0, 0}, new float[]{cameraX, cameraY, -1});
    }

    public static int[] getPixelColor(Triangle[] triangles, float[] t) {
        int tmin = 0;
        int i = 0;
        boolean checker = false;
        while (i < t.length) {
            if (t[i] > 0) {
                if (t[i] <= t[tmin] || t[tmin] < 0) {
                    checker = true;
                    tmin = i;
                }

            }
            i++;
        }
        if (!checker) return new int[]{-100, 0, 0};

        return triangles[tmin].getColor();
    }
}
