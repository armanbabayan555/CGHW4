package CGHW4;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import static CGHW4.PerspectiveCamera.*;
import static CGHW4.Triangle.*;

public class Images {
    private static final int width = 800;
    private static final int height = 600;


    public static void main(String[] args) {
        Frame frame = new Frame("Arman Babayan HW4");

        frame.setSize(width, height);
        frame.setLocation(300, 300);
        frame.setVisible(true);

        Graphics graphics = frame.getGraphics();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        solidSetRaster(image);

        Triangle triangle1 = new Triangle(new float[]{1.3f, 0.3f, -1}, new float[]{0.6f, -0.5f, -1}, new float[]{-0.5f, -0.5f, -1}, new int[]{255, 102, 178});
        Triangle triangle2 = new Triangle(new float[]{-3.5f, 0.5f, -1}, new float[]{-1.0f, -0.5f, -1}, new float[]{0.5f, 1.25f, -1}, new int[]{0, 255, 128});
        Triangle triangle3 = new Triangle(new float[]{0, 2.7f, -1}, new float[]{-1.3f, 1.5f, -1}, new float[]{0.43f, 1.23f, -1}, new int[]{102, 178, 255});

        Triangle[] triangleArray = new Triangle[]{triangle1, triangle2, triangle3};

        WritableRaster raster = image.getRaster();

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                Ray ray = new PerspectiveCamera().constructRayThroughPixel(i, j);
                float[] t = findIntersection(triangleArray, ray);
                int[] color = getPixelColor(triangleArray, t);
                if (color[0] >= 0) raster.setPixel(i, j, color);
            }

        graphics.drawImage(image, 0, 0, (img1, infoflags, x, y, width, height) -> false);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                graphics.dispose();
                System.exit(0);
            }
        });
    }

    public static void solidSetRaster(BufferedImage img) {
        WritableRaster raster = img.getRaster();
        int[] pixel = new int[3];

        int y = 0;
        while (y < height) {
            int x;
            for (x = 0; x < width; x++) raster.setPixel(x, y, pixel);
            y++;
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}



