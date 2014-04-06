package main.java.schema;

import main.java.engine.objects.tower.SimpleTower;

import java.awt.Dimension;


/**
 * 
 * This is a settings object for a specific type of Tower like a Fire Tower, and
 * at a high level is a wrapper for a bunch of key value pairs that the Engine
 * will need to reference to create TDObjects. This class is not a specific
 * instance of a Tower. Please refer to the Game Engine's TDObjects for the
 * objects related to ones you will see onscreen.
 */
public class SimpleTowerSchema extends TowerSchema {
    private static final Class<SimpleTower> MY_CONCRETE_TYPE = SimpleTower.class;

    private String myImage;
	private double myAttackRate;
	private double myAttackVelocity;
	private double myDamage;
	private double mySplashRadius;
	private double myRange;
	private Dimension myBoxSize;
	private int myAttackType; // used to determine amplification of damage
	private double myCost;

    public SimpleTowerSchema() {
        super(MY_CONCRETE_TYPE);
    }

    public double getMyAttackRate() {
		return myAttackRate;
	}

	public void setMyAttackRate(double myAttackRate) {
		this.myAttackRate = myAttackRate;
	}

	public double getMyAttackVelocity() {
		return myAttackVelocity;
	}

	public void setMyAttackVelocity(double myAttackVelocity) {
		this.myAttackVelocity = myAttackVelocity;
	}

	public double getMyDamage() {
		return myDamage;
	}

	public void setMyDamage(double myDamage) {
		this.myDamage = myDamage;
	}

	public double getMySplashRadius() {
		return mySplashRadius;
	}

	public void setMySplashRadius(double mySplashRadius) {
		this.mySplashRadius = mySplashRadius;
	}

	public double getMyRange() {
		return myRange;
	}

	public void setMyRange(double myRange) {
		this.myRange = myRange;
	}

	public Dimension getMyBoxSize() {
		return myBoxSize;
	}

	public void setMyBoxSize(Dimension myBoxSize) {
		this.myBoxSize = myBoxSize;
	}

	public int getMyAttackType() {
		return myAttackType;
	}

	public void setMyAttackType(int myAttackType) {
		this.myAttackType = myAttackType;
	}

	public double getMyCost() {
		return myCost;
	}

	public void setMyCost(double myCost) {
		this.myCost = myCost;
	}

	public String getMyImage() {
		return myImage;
	}

	public void setMyImage(String myImage) {
		this.myImage = myImage;
	}
	
}
