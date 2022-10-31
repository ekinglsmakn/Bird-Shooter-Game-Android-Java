package com.ekinakin.birdshootergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private Boolean isPlaying;
    private int screenX;
    private int screenY;
    private Background background1;
    private Background background2;
    private Paint paint;
    public static float screenSizeX;
    public static float screenSizeY;
    private Plane plane;
    private List<Bullet> bulletList;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;
        screenSizeX = 1920f / screenX;
        screenSizeY = 1080f / screenY;
        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        plane = new Plane(this,screenY, getResources());
        bulletList = new ArrayList<>();
        background2.x = screenX;
        paint = new Paint();

    }

    @Override
    public void run() {

        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            canvas.drawBitmap(plane.getPlane(), plane.x, plane.y, paint);

            for(Bullet bullet : bulletList){
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);
            }

            getHolder().unlockCanvasAndPost(canvas);

        }
    }

    private void update() {
        background1.x -= 10 * screenSizeX;
        background2.x -= 10 * screenSizeX;
        if (background1.x + background1.background.getWidth() < 0) {
            background1.y = screenX;
        }
        if (background2.x + background2.background.getWidth() < 0) {

            background2.x = screenX;
        }

        if(plane.goUp){
            plane.y -= 30 * screenSizeY;
        } else
            plane.y += 30 * screenSizeY;

        if(plane.y < 0){
            plane.y = 0;
        }

        if(plane.y >= screenY - plane.heightPlane){
            plane.y = screenY - plane.heightPlane;
        }

        List<Bullet> bullets = new ArrayList<>();
        for(Bullet bullet : bulletList){
            if(bullet.x > screenX){
                bullets.add(bullet);

            }
            bullet.x += 50 * screenSizeX;
        }

        for(Bullet bullet : bullets){
           bulletList.remove(bullet);
        }
    }

    private void sleep() {
        try {
            thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(event.getX() < screenX / 2){
                    plane.goUp = true;
                } break;
            case MotionEvent.ACTION_UP:
                plane.goUp = false;
                if(event.getX() > screenX / 2 ){
                    plane.fire++;

                }
                break;


        }
        return true;
    }

    public void bullet() {
        Bullet bullet = new Bullet(getResources());
        bullet.x = plane.x + plane.widthPlane;
        bullet.y = plane.y + (plane.heightPlane / 2);
        bulletList.add(bullet);

    }
}
