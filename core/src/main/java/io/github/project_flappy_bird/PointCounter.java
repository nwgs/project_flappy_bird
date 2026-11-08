package io.github.project_flappy_bird;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PointCounter {

    int x, y;
    BitmapFont font;

    PointCounter (int x, int y){
        this.x = x;
        this.y = y;

        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(Color.WHITE);
    }
    void draw(Batch batch, int count_Of_Points){
        font.draw(batch, "Score:" + count_Of_Points, x, y);
    }

    void discope(){
        font.dispose();
    }
}
