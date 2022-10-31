package com.ekinakin.birdshootergame;

import static com.ekinakin.birdshootergame.GameView.screenSizeX;
import static com.ekinakin.birdshootergame.GameView.screenSizeY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Plane {
    public int x = 0;
    public int y = 0;
    public int heightPlane = 0;
    public int widthPlane = 0;
    public int wingMove = 0;
    public boolean goUp = false;
    public int fire = 0;
    public int fireCount = 1;
    Bitmap plane1;
    Bitmap plane2;
    Bitmap planefire;
    Bitmap planefire2;
    Bitmap planefire3;
    Bitmap planefire4;
    Bitmap planefire5;
    private GameView gameView;

    public Plane(GameView gameView, int screenY, Resources res) {
        this.gameView = gameView;
        plane1 = BitmapFactory.decodeResource(res, R.drawable.plane1);
        plane2 = BitmapFactory.decodeResource(res, R.drawable.plane2);

        widthPlane = plane1.getWidth();
        heightPlane = plane1.getHeight();

        //uçağı 4 kat küçültüyoruz;
        widthPlane /= 4;
        heightPlane /= 4;

        widthPlane *= (int) screenSizeX;
        heightPlane *= (int) screenSizeY;

        plane1 = Bitmap.createScaledBitmap(plane1, widthPlane, heightPlane, false);
        plane2 = Bitmap.createScaledBitmap(plane2, widthPlane, heightPlane, false);

        planefire = BitmapFactory.decodeResource(res, R.drawable.planefire);
        planefire2 = BitmapFactory.decodeResource(res, R.drawable.planefire2);
        planefire3 = BitmapFactory.decodeResource(res, R.drawable.planefire3);
        planefire4 = BitmapFactory.decodeResource(res, R.drawable.planefire4);
        planefire5 = BitmapFactory.decodeResource(res, R.drawable.planefire5);

        planefire = Bitmap.createScaledBitmap(planefire, widthPlane, heightPlane, false);
        planefire2 = Bitmap.createScaledBitmap(planefire2, widthPlane, heightPlane, false);
        planefire3 = Bitmap.createScaledBitmap(planefire3, widthPlane, heightPlane, false);
        planefire4 = Bitmap.createScaledBitmap(planefire4, widthPlane, heightPlane, false);
        planefire5 = Bitmap.createScaledBitmap(planefire5, widthPlane, heightPlane, false);

        y = screenY / 2;
        x = (int) (64 * screenSizeX);
    }

    Bitmap getPlane() {

        if (fire != 0) {
            if (fireCount == 1) {
                fireCount++;
                return planefire;
            }
            if (fireCount == 2) {
                fireCount++;
                return planefire2;
            }
            if (fireCount == 3) {
                fireCount++;
                return planefire3;
            }
            if (fireCount == 4) {
                fireCount++;
                return planefire4;
            }

            fireCount = 1;
            fire--;
            gameView.bullet();
            return planefire5;

        }

        if (wingMove == 0) {
            wingMove++;
            return plane1;
        } else
            wingMove--;
        return plane2;
    }
}
