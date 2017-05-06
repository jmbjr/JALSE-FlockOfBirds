package jmbjr.simland.panels;

import static jalse.entities.Entities.isMarkedAsType;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import jalse.DefaultJALSE;
import jalse.JALSE;

import jmbjr.simland.entities.FarmObject;
import jmbjr.simland.entities.Field;
import jmbjr.simland.entities.Info;
import jmbjr.simland.entities.animals.Animal;
import jmbjr.simland.entities.drawlayer.AnimalLayer;
import jmbjr.simland.entities.drawlayer.GroundLayer;
import jmbjr.simland.entities.drawlayer.PlantLayer;

/**
 * @author John Boyle, boylejm@gmail.com, https://github.com/jmbjr
 * Main class for displaying the farm entities
 */
@SuppressWarnings("serial")
public class InfoPanel extends JPanel {

	 private final JALSE jalse;
    /**
     * TICK_INTERVAL should always be divided by 30 (fps)
     */
    public static final int TICK_INTERVAL = 1000 / 30;
	    
    /**
     * WIDTH = farm width in pixels
     */
    public static final int WIDTH = 200;
    /**
     * HEIGHT = farm height in pixels
     */
    public static final int HEIGHT = 200;
    
    private FarmObject farmobject = null;
    
	 public InfoPanel(FarmObject farmobject) {
		jalse = new DefaultJALSE.Builder().setManualEngine().build();
		createEntities();
		getInfo().newEntity(farmobject);
		setPreferredSize(getInfo().getSize());
		JLabel label = new JLabel(farmobject.getName());
		this.add(label, BorderLayout.PAGE_END);
		// check if animal
		JLabel ilabel = new JLabel(new ImageIcon(((Animal) farmobject).getImage()));
		this.farmobject = farmobject;
        this.add(ilabel);
        this.setLocation(50,50);
        this.setVisible(true);
        
	}
    
    private Info getInfo() {
    	return jalse.getEntityAsType(Info.ID, Info.class);
    }
    
    private void createEntities() {
		// Create field
		final Info info = jalse.newEntity(Info.ID, Info.class);
		info.setSize(new Dimension(WIDTH, HEIGHT));
    }
}
