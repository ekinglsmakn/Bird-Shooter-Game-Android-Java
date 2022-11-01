package com.ekinakin.birdshootergame;

import static com.ekinakin.birdshootergame.GameView.screenSizeX;
import static com.ekinakin.birdshootergame.GameView.screenSizeY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Bullet {
    int x = 0;
    int y = 0;
    int widthBullet;
    int heightBullet;
    Bitmap bullet;

    Bullet(Resources res){
        bullet = BitmapFactory.decodeResource(res, R.drawable.bullet);
        int widthBullet = bullet.getWidth();
        int heightBullet = bullet.getHeight();
        widthBullet /=4;
        heightBullet /=4;

        widthBullet *= (int) screenSizeX;
        heightBullet *= (int) screenSizeY;

        bullet = Bitmap.createScaledBitmap(bullet, widthBullet, heightBullet, false);

    }

    Rect getCrushControl(){
        return new Rect(x, y, x+widthBullet, y+heightBullet);
    }
}
