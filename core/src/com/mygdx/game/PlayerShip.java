package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

class PlayerShip extends Ship{

    int lives;

    public PlayerShip(float movementSpeed, int shield,
                      float xCentre, float yCentre,
                      float width, float height,
                      float laserWidth, float laserHeight,
                      float laserMovementSpeed, float timeBetweenShots,
                      TextureRegion shipTextureRegion,
                      TextureRegion shieldTextureRegion,
                      TextureRegion laserTextureRegion) {
        super(movementSpeed, shield, xCentre, yCentre,
                width, height, laserWidth, laserHeight,
                laserMovementSpeed, timeBetweenShots, shipTextureRegion,
                shieldTextureRegion, laserTextureRegion);
        lives = 3;
    }

    @Override
    public Laser[] fireLasers() {
        Laser[] laser = new Laser[2];
        laser[0] = new Laser(boundingBox.x+ boundingBox.width*0.18f,
                boundingBox.y +boundingBox.height*0.45f,
                laserWidth, laserHeight, laserMovementSpeed,
                laserTextureRegion);
        laser[1] = new Laser(boundingBox.x+boundingBox.width*0.82f,
                boundingBox.y +boundingBox.height*0.45f,
                laserWidth, laserHeight, laserMovementSpeed,
                laserTextureRegion);

        timeSinceLastShot = 0;

        return laser;
    }
}
