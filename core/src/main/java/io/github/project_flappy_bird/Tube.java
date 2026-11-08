package io.github.project_flappy_bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Tube {
    //переменные
    Texture texture_Upper_Tube;
    Texture texture_Down_Tube;
    int x;
    int gapY;
    int gapHeight = 400;
    int paddind = 50;
    final int width = 200;
    final int height = 700;
    int distanceBetweenTubes;
    int speed = 10;
    boolean is_Get_Point;

    //классы и их объекты
    Random random;
    Main main;

    //конструкторы
    public Tube(int countTubes, int tubeIdx){
        random = new Random();

        //иннициализация труб по оси Y
        gapY = gapHeight / 2 + paddind + random.nextInt(main.SCR_HEIGHT - 2 * (paddind + gapHeight / 2));
        //ин. дистанции между труб
        distanceBetweenTubes = (main.SCR_HEIGHT / 2 + width) / (countTubes - 1);
        //ин. труб по оси x
        x = distanceBetweenTubes * tubeIdx + main.SCR_WIDTH / 2;
        //текстуры
        texture_Upper_Tube = new Texture("tubesPics/tube_flipped.png");
        texture_Down_Tube = new Texture("tubesPics/tube.png");
    }
    //методы
    public void draw(Batch batch){
        batch.draw(texture_Upper_Tube, x, gapY + gapHeight / 2, width, height);
        batch.draw(texture_Down_Tube, x, gapY - gapHeight / 2 - height, width, height);
    }
    public void discope(){
        texture_Upper_Tube.dispose();
        texture_Down_Tube.dispose();
    }
    public void move(){
        x -= speed;
        if (x < -width){
            is_Get_Point = false;
            x = main.SCR_WIDTH - distanceBetweenTubes;
            gapY = gapHeight / 2 + paddind + random.nextInt(main.SCR_HEIGHT - 2 * (paddind + gapHeight / 2));
        }
    }

    public boolean is_Hit(Bird bird) {
        //коллизия нижних труб
        if (bird.y <= gapY - gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x + width)
            return true;
        //коллизия верхних труб
        if (bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x + width)
            return true;
        //default
        return false;
    }

    public boolean adding_Score(Bird bird){

       return  !is_Get_Point && bird.x >= x + width;

    }

    public void set_Point(){
        is_Get_Point = true;
    }

}//end of class
