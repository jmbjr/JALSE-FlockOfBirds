package jmbjr.simland;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import jalse.entities.Entity;
import jmbjr.simland.entities.Adult;
import jmbjr.simland.entities.Animal;
import jmbjr.simland.entities.Child;
import jmbjr.simland.entities.Rester;
import jmbjr.simland.entities.Grazer;

public class FarmAnimalProperties {

    private static class AnimalProperties {

	private final AtomicReference<Color> colour;
	private final AtomicInteger sightRange;
	private final AtomicLong speed;
	private final AtomicInteger health;
	private final AtomicInteger size;
	

	AnimalProperties(final Color colour, final int sightRange, final double speed, final int health, final int size) {
	    this.colour = new AtomicReference<>(colour);
	    this.sightRange = new AtomicInteger(sightRange);
	    this.speed = new AtomicLong(Double.doubleToLongBits(speed));
	    this.health = new AtomicInteger(health);
	    this.size = new AtomicInteger(size);
	}

    }

    private static final int SIZE_ADULT = 50;
    
    private static final int SIZE_CHILD = 15;

    private static AtomicLong infectionTime = new AtomicLong(Double.doubleToLongBits(5));

    private static AtomicLong starveTime = new AtomicLong(Double.doubleToLongBits(10));

    private static AtomicInteger population = new AtomicInteger(3);

    private static Map<Class<?>, AnimalProperties> props = new HashMap<>();

    static {
	props.put(Animal.class, new AnimalProperties(Color.WHITE, 75, 3.0,100, SIZE_ADULT));
	props.put(Grazer.class, new AnimalProperties(new Color(100,50,15), 75, 3.0,100, SIZE_ADULT));
	props.put(Rester.class, new AnimalProperties(new Color(40,30,20), 75, 3.0,100, SIZE_ADULT/2));
	props.put(Child.class, new AnimalProperties(new Color(0,0,0), 500, 6.0,100, SIZE_CHILD));
	props.put(Adult.class, new AnimalProperties(new Color(0,0,0), 75, 3.0,100, SIZE_ADULT));
    }

    public static Color getColour(final Class<? extends Entity> type) {
    	return props.get(type).colour.get();
    }

    public static double getInfectionTime() {
	return Double.longBitsToDouble(infectionTime.get());
    }
    
	public static int getHealth(Class<? extends Entity> type) {
		return  props.get(type).health.get();
	}
	
    public static int getPopulation() {
	return population.get();
    }

    public static int getSightRange(final Class<? extends Entity> type) {
	return props.get(type).sightRange.get();
    }

    public static int getSize() {
	return SIZE_ADULT;
    }
    
    public static int getSize(final Class<? extends Entity> type) {
	return props.get(type).size.get();
    }

    public static int getSizeChild() {
	return SIZE_CHILD;
    }

    public static double getSpeed(final Class<? extends Entity> type) {
	return Double.longBitsToDouble(props.get(type).speed.get());
    }

    public static double getStarveTime() {
	return Double.longBitsToDouble(starveTime.get());
    }



    public static void setInfectionTime(final double infectionTime) {
	FarmAnimalProperties.infectionTime.set(Double.doubleToLongBits(infectionTime));
    }

    public static void setPopulation(final int population) {
	FarmAnimalProperties.population.set(population);
    }


    public static void setSpeed(final Class<? extends Animal> type, final double speed) {
	props.get(type).speed.set(Double.doubleToLongBits(speed));
    }

    public static void setStarveTime(final double starveTime) {
	FarmAnimalProperties.starveTime.set(Double.doubleToLongBits(starveTime));
    }


}
