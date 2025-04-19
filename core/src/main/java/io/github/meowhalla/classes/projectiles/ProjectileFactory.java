package io.github.meowhalla.classes.projectiles;

import io.github.meowhalla.classes.characters.CharacterContext;

import java.util.List;


abstract public class ProjectileFactory {

    // projectile stats
    protected int power;
    protected String fileName;
    protected float radius;

    public ProjectileFactory(int power, String fileName, float radius) {
        this.power = power;
        this.fileName = fileName;
        this.radius = radius;
    }

    public abstract List<ProjectileContext> createProjectiles(CharacterContext owner);
}
