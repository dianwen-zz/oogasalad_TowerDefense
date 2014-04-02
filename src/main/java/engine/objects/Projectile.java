package main.java.engine.objects;

import main.java.engine.objects.monster.Monster;
import main.resources.jgame.JGObject;


public class Projectile extends TDObject {

    public static final int TOWER_PROJECTILE_CID = 10;
    public static final double DEFAULT_SPEED = 20;

    private double myDamage;
    
    /**
     * Create projectile with specific src coordinates, xspeed, and yspeed
     * @param x src x-coor
     * @param y src y-coor
     * @param xspeed
     * @param yspeed
     */
    public Projectile (double x, double y, double xspeed, double yspeed, double damage) {
        super("projectile", x, y, TOWER_PROJECTILE_CID, "red_bullet", xspeed, yspeed,
              JGObject.expire_off_view);
        myDamage = damage;
    }

    /**
     * Creates projectile heading in given angle with default speed.
     * 
     * @param x src x-coor
     * @param y src y-coor
     * @param angle Math.atan2(destX - srcX, destY - srcY)
     */
    public Projectile (double x, double y, double angle, double damage) {
        super("projectile", x, y, TOWER_PROJECTILE_CID, "red_bullet",
              DEFAULT_SPEED * Math.sin(angle),
              DEFAULT_SPEED * Math.cos(angle),
              JGObject.expire_off_view);
        myDamage = damage;
    }
    
    
    @Override
    public void hit (JGObject obj) {
        if (and(obj.colid, Monster.MONSTER_CID)) {
            ((Monster) obj).takeDamage(myDamage);
        }
    }

}
