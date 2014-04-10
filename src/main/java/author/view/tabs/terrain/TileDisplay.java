package main.java.author.view.tabs.terrain;

import static main.java.author.util.ActionListenerUtil.actionListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.java.author.view.tabs.terrain.types.TileObject;

public class TileDisplay extends JPanel {

	private static final int SCALE_PIXEL_SIZE = 16; // pixel size for jbutton icon display
	private String myBitmapFile;
	private TileSelectionManager myTileManager;
	private Image[][] myImages; 
	private int pixelSize;
	
	public TileDisplay(TileSelectionManager tileManager, String bitmapFile, int pixels) {
		myTileManager = tileManager;
		myBitmapFile = bitmapFile;
		pixelSize = pixels;
		initResources();
		displayTiles();
		setVisible(true);
	}
	
	/**
	 * Initializes resources to be used 
	 */
	private void initResources() {
		myImages = parseBitmap(Tile.DEFAULT_IMAGE_PACKAGE, myBitmapFile);
	}
	
	/**
	 * Reads through a bitmap (currently used for tile backgrounds) and outputs
	 * a 2D array of image objects
	 * @param bitmapPckg the package where the bitmap is located
	 * @param bitmapName the name of the bitmap file
	 * @return a 2D array of image objects within the bitmap
	 */
	private Image[][] parseBitmap(String bitmapPckg, String bitmapName) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(bitmapPckg + bitmapName));
			int imageWidth = img.getWidth();
			int imageHeight = img.getHeight();
			Image [][] myImageArray = new Image[imageHeight/pixelSize][imageWidth/pixelSize];
			for (int i = 0; i < imageHeight/pixelSize; i++) {
				for (int j = 0; j < imageWidth/pixelSize; j++) {
					myImageArray[i][j] = img.getSubimage(j*pixelSize, i*pixelSize, pixelSize, pixelSize);
				}
			}
			return myImageArray;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Allows the user to see the tiles read in from the bitmap,
	 * displayed through a GridBagLayout on a JPanel
	 */
	public void displayTiles() {
		JPanel tileView = new JPanel();
		tileView.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		for (int i = 0; i < myImages.length; i ++) {
			for (int j = 0; j < myImages[0].length; j++) {
				if (c.gridx > 8) {
					c.gridx = 0;
					c.gridy++;
				} 
				Image im = myImages[i][j];
				Image scaledIm = im.getScaledInstance(SCALE_PIXEL_SIZE, SCALE_PIXEL_SIZE, Image.SCALE_DEFAULT); // scale down image

				TileObject imgDisplayObj = new TileObject(im);
				imgDisplayObj.addActionListener(actionListener(this, "updateSelection"));
				imgDisplayObj.setIcon(new ImageIcon(scaledIm)); // place scaled image as jbutton icon
				tileView.add(imgDisplayObj, c); // grid layout of jbutton/images
				c.gridx++;
			}
		}
		add(tileView);
	}
	
	/**
	 * Obtains the side length of images in the bitmap, in pixels
	 */
	public int getPixelSize() {
		return pixelSize;
	}
	
	/**
	 * Allows for a scrollable view of the tiles
	 */
	public JScrollPane getTileScrollPane() {
		JScrollPane myScrollPane = new JScrollPane(this);
		myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		myScrollPane.setPreferredSize(new Dimension(275, 350));
		return myScrollPane;
	}
	
	/**
	 * Action Listener, called when a TileObject within this TileDisplay is clicked
	 * @param e an ActionEvent called by a TileObject source
	 */
	public void updateSelection(ActionEvent e) {
		TileObject selectedTile = (TileObject) e.getSource();
		myTileManager.getCanvas().setSelectedTileObj(selectedTile);
		myTileManager.getTileEditPanel().setImageAngle(0);
		myTileManager.getTileEditPanel().update(myTileManager.getTileEditPanel().getGraphics());
	}
	
}