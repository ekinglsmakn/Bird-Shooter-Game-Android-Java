package com.ekinakin.birdshootergame;

import static com.ekinakin.birdshootergame.GameView.screenSizeX;
import static com.ekinakin.birdshootergame.GameView.screenSizeY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Bird {
    public int birdFast = 20;
    int x = 0;
    int y = 0;
    int birdWidth;
    int birdHeight;
    int birdCounter = 0;
    public boolean hitTheBird = true;
    Bitmap bird1, bird2, bird3, bird4;

    Bird(Resources res) {
        bird1 = BitmapFactory.decodeResource(res, R.drawable.bird1);
        bird2 = BitmapFactory.decodeResource(res, R.drawable.bird2);
        bird3 = BitmapFactory.decodeResource(res, R.drawable.bird3);
        bird4 = BitmapFactory.decodeResource(res, R.drawable.bird4);

        birdWidth = bird1.getWidth();
        birdHeight = bird1.getHeight();

        birdWidth /= 6;
        birdHeight /= 6;

        birdWidth *= (int) screenSizeX;
        birdHeight *= (int) screenSizeY;

        bird1 = Bitmap.createScaledBitmap(bird1, birdWidth, birdHeight, false);
        bird2 = Bitmap.createScaledBitmap(bird2, birdWidth, birdHeight, false);
        bird3 = Bitmap.createScaledBitmap(bird3, birdWidth, birdHeight, false);
        bird4 = Bitmap.createScaledBitmap(bird4, birdWidth, birdHeight, false);

        y = -birdHeight;
    }

    Bitmap getBird1() {

        if (birdCounter == 1) {
            birdCounter++;
            return bird1;
        }
        if (birdCounter == 2) {
            birdCounter++;
            return bird2;
        }
        if (birdCounter == 3) {
            birdCounter++;
            return bird3;
        }
        birdCounter = 1;
        return bird4;
    }

    Rect getCrushControl(){
        return new Rect(x, y, x+birdWidth, y+birdHeight);
    }

}
