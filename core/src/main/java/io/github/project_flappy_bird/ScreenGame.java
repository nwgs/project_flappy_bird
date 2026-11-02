package io.github.project_flappy_bird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {

    Main main;
    Bird bird;

    ScreenGame(Main main) {
        this.main =main;
        bird = new Bird(0, 0, 10, 250, 200);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }

        bird.fly();

        ScreenUtils.clear(1, 0, 0, 1);

        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);

        main.batch.begin();
        bird.draw(main.batch);
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
    }
}
