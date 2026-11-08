package io.github.project_flappy_bird;
import static io.github.project_flappy_bird.Main.SCR_HEIGHT;
import static io.github.project_flappy_bird.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {

    PointCounter pointCounter;
    Main main;
    Bird bird;
    Tube tube;
    Tube[] tubes;

    int countTubes = 3;

    boolean game_Over;

    int score;

    final int pCMT = 60;

    final int pCMR = 400;


    ScreenGame(Main main) {

        this.main = main;
        bird = new Bird(0, 0, 10, 250, 200);
        tubes = new Tube[countTubes];
        inTubes();

    }

    @Override
    public void show() {
        game_Over = false;
        score = 0;

    }

    @Override
    public void render(float delta) {

        pointCounter = new PointCounter(SCR_WIDTH - pCMR, SCR_HEIGHT - pCMT);

        if (Gdx.input.justTouched()) {
            bird.onClick();
        }

        bird.fly();


        for (Tube tube : tubes) {
            if (!bird.is_In_Screen()) {
                System.out.println("fall");
                game_Over = true;
            }
        }
        for (Tube tube : tubes) {
            tube.move();
            if (tube.is_Hit(bird)) {
                System.out.println("hit");
                game_Over = true;
            }  else if (tube.adding_Score(bird)) {
                tube.set_Point();
                score += 1;
                System.out.println(score);
            }
        }
        ScreenUtils.clear(1, 0, 0, 1);

        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);

        main.batch.begin();

        for (Tube tube : tubes) tube.draw(main.batch);
        bird.draw(main.batch);
        pointCounter.draw(main.batch, score);
        main.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        bird.dispose();
        pointCounter.discope();
        for (int i = 0; i < countTubes; i++) {
            tubes[i].discope();
        }

    }

    void inTubes() {
        tubes = new Tube[countTubes];
        for (int i = 0; i < countTubes; i++) {
            tubes[i] = new Tube(countTubes, i);
        }
    }
} //end class



