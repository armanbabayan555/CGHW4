package CGHW4;

import static java.util.stream.IntStream.range;

public class Triangle {
    private final float[] A;
    private final float[] B;
    private final float[] C;
    private final int[] color;

    public Triangle(float[] a, float[] b, float[] c, int[] color) {
        this.A = a;
        this.B = b;
        this.C = c;
        this.color = color;
    }

    public float[] normal() {
        float cx = A[0] - B[0];
        float cy = A[1] - B[1];
        float cz = A[2] - B[2];

        float dx = A[0] - C[0];
        float dy = A[1] - C[1];
        float dz = A[2] - C[2];

        float[] normal = new float[]{cy * dz - dy * cz, cz * dx - dz * cx, cx * dy - dx * cy};

        return normalize(normal);
    }

    public float[] normalize(float[] array) {
        float[] newArray = new float[array.length];

        float norm = 0;

        for (float v : array) norm += v * v;

        int i = 0;
        while (i < array.length) {
            newArray[i] = array[i] / norm;
            i++;
        }
        return newArray;
    }

    public float dotProduct(float[] array1, float[] array2) {
        float sum = 0;

        int i = 0;
        while (i < array1.length) {
            sum = sum + array1[i] * array2[i];
            i++;
        }

        return sum;
    }


    public float intersect(Ray r) {
        if (dotProduct(r.getDir(), normal()) != 0) {
            float t = (dotProduct(A, normal()) - dotProduct(r.getPos(), normal())) / dotProduct(r.getDir(), normal());

            if (t < 0) return -1;

            float[] AB = new float[]{A[0] - B[0], A[1] - B[1], A[2] - B[2]};
            float[] AC = new float[]{A[0] - C[0], A[1] - C[1], A[2] - C[2]};
            float[] P = new float[3];

            range(0, 3).forEach(i -> P[i] = r.getPos()[i] + r.getDir()[i] * t - A[i]);

            float arr1 = (P[0] * AB[1] - P[1] * AB[0]) / (AB[0] * AC[1] - AB[1] * AC[0]);
            float arr2 = (-P[0] - arr1 * AC[0]) / (AB[0]);

            return (arr1 > 0 && arr2 > 0) && (arr1 + arr2 <= 1) ? t : -1;
        } else return 5;

    }

    public static float[] findIntersection(Triangle[] triangles, Ray r) {
        float[] t = new float[triangles.length];

        range(0, triangles.length).forEach(i -> t[i] = triangles[i].intersect(r));

        return t;
    }

    public int[] getColor() {
        return color;
    }

}
