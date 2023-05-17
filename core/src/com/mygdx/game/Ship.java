package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

abstract class Ship {

    //ship characteristics
    float movementSpeed; //world units per second
    int shield;

    //position & dimension
    Rectangle boundingBox;

    //laser info
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot = 0;
    float laserMovementSpeed;

    //graphics
    TextureRegion shipTextureRegion, shieldTextureRegion, laserTextureRegion;

    public Ship(float movementSpeed, int shield,
                float xCentre, float yCentre,
                float width, float height,
                float laserWidth, float laserHeight,
                float laserMovementSpeed,
                float timeBetweenShots,
                TextureRegion shipTextureRegion,
                TextureRegion shieldTextureRegion,
                TextureRegion laserTextureRegion)
    {
        this.movementSpeed = movementSpeed;
        this.shield = shield;
        this.boundingBox = new Rectangle(xCentre - width/2, yCentre - height/2, width, height);
        this.laserWidth = laserWidth;
        this.laserHeight = laserHeight;
        this.laserMovementSpeed = laserMovementSpeed;
        this.timeBetweenShots = timeBetweenShots;
        this.shipTextureRegion = shipTextureRegion;
        this.shieldTextureRegion = shieldTextureRegion;
        this.laserTextureRegion = laserTextureRegion;
    }

    public void update(float deltaTime){
        timeSinceLastShot += deltaTime;
    }

    public boolean hitAndCheckDestroyed(Laser laser) {
        if (shield > 0) {
            shield --;
            return false;
        }
        return true;
    }
    public boolean intersects(Rectangle otherRectanble) {
        return boundingBox.overlaps(otherRectanble);
    }

    public void draw(Batch batch) {
        batch.draw(shipTextureRegion,
                boundingBox.x, boundingBox.y,
                boundingBox.width, boundingBox.height);
        if (shield > 0) {
            batch.draw(shieldTextureRegion,
                    boundingBox.x, boundingBox.y,
                    boundingBox.width, boundingBox.height);
        }
    }

    public void translate(float xChange, float yChange) {
        boundingBox.setPosition(boundingBox.x+xChange, boundingBox.y+yChange);
    }
    public boolean canFireLaser(){
        return (timeSinceLastShot - timeBetweenShots >= 0);
    }

    public abstract Laser[] fireLasers();
}
