package com.hunter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.hunter.game.HunterGame;
import com.hunter.generators.WorldGenerator;
import com.hunter.objects.PlayerSprite;

public class GameScreen implements Screen {

    HunterGame game;

    public GameScreen(HunterGame game){
        this.game = game;
        WorldGenerator worldGenerator = new WorldGenerator();
        worldGenerator.createMap();
        game.map = worldGenerator.generateMap();
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false, 120, 120);
        game.camera.update();
        float unitScale = 1 /5f;
        game.renderer = new OrthogonalTiledMapRenderer(game.map, unitScale);
        game.renderer.setView(game.camera);
        game.batch = new SpriteBatch();
        game.playerSprite = new PlayerSprite(game.batch,120,120);
        game.camera.position.set(game.playerSprite.getX(),game.playerSprite.getY(),0);

    };

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.renderer.setView(game.camera);
        game.renderer.render();
        game.batch.begin();
        game.playerSprite.generalMovement(delta);
        game.camera.position.set(game.playerSprite.getX(),game.playerSprite.getY(),0);
        game.batch.end();

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

    }
}
