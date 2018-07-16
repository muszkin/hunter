package com.hunter.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerSprite {

    private TextureRegion[][] textureRegion;
    private Animation<TextureRegion> walkTop;
    private Animation<TextureRegion> walkBottom;
    private Animation<TextureRegion> walkRight;
    private Animation<TextureRegion> walkLeft;
    private Animation<TextureRegion> noWalkFace;
    private Animation<TextureRegion> noWalkBack;
    private int x;
    private int y;
    private float stateTime = 0;
    private SpriteBatch batch;

    public PlayerSprite(SpriteBatch batch,int x ,int y){
        this.textureRegion = TextureRegion.split(new Texture(Gdx.files.internal("image.png")),192,192);
        this.walkTop = new Animation(0.1f,(textureRegion[1][1]),(textureRegion[1][2]),(textureRegion[1][3]));
        this.walkBottom = new Animation(0.1f,(textureRegion[0][1]),(textureRegion[0][2]),(textureRegion[0][3]));
        this.walkLeft = new Animation(0.1f,(textureRegion[2][0]),(textureRegion[2][1]),(textureRegion[2][2]));
        this.walkRight = new Animation(0.1f,(textureRegion[2][3]),(textureRegion[3][0]),(textureRegion[3][1]));
        this.noWalkFace = new Animation(0.1f,textureRegion[0][0]);
        this.noWalkBack = new Animation(0.1f,textureRegion[1][0]);
        this.walkTop.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        this.walkBottom.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        this.walkRight.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        this.walkLeft.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        this.batch = batch;
        this.x = x;
        this.y = y;
    }


    public Animation getWalkTop() {
        return walkTop;
    }

    public Animation getWalkBottom() {
        return walkBottom;
    }

    public Animation getWalkRight() {
        return walkRight;
    }

    public Animation getWalkLeft() {
        return walkLeft;
    }

    public Animation getNoWalkFace() {
        return noWalkFace;
    }

    public Animation getNoWalkBack() {
        return noWalkBack;
    }

    public void generalMovement(float deltaTime){
        stateTime += deltaTime;
        TextureRegion currentFrame;
        if(Gdx.input.isKeyPressed(Input.Keys.D) || (Gdx.input.isKeyPressed(Input.Keys.RIGHT)))
        {
            this.x += 5;
            currentFrame = (TextureRegion) getWalkRight().getKeyFrame(stateTime,true);
            batch.draw(currentFrame,getX(),getY());
        }else if(Gdx.input.isKeyPressed(Input.Keys.A) || (Gdx.input.isKeyPressed(Input.Keys.LEFT)))
        {
            this.x -= 5;
            currentFrame = (TextureRegion) getWalkLeft().getKeyFrame(stateTime,true);
            batch.draw(currentFrame,getX(),getY());
        }else if(Gdx.input.isKeyPressed(Input.Keys.S) || (Gdx.input.isKeyPressed(Input.Keys.DOWN)))
        {
            this.y -= 5;
            currentFrame = (TextureRegion) getWalkBottom().getKeyFrame(stateTime,true);
            batch.draw(currentFrame,getX(),getY());
        }else if(Gdx.input.isKeyPressed(Input.Keys.W) || (Gdx.input.isKeyPressed(Input.Keys.UP)))
        {
            this.y += 5;
            currentFrame = (TextureRegion) getWalkTop().getKeyFrame(stateTime,true);
            batch.draw(currentFrame,getX(),getY());
        }else{
            currentFrame = (TextureRegion) getNoWalkFace().getKeyFrame(stateTime,true);
            batch.draw(currentFrame,getX(),getY());
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
