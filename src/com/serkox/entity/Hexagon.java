package com.serkox.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Hexagon extends JButton implements MouseListener {

	private int id;
	private int posX;
	private int posY;
	private boolean isObstacle;
	private Texture texture;
	private boolean containCapital;
	private Unit unit;
	private ArrayList<Hexagon> neighbors;

	public Hexagon(int p_Id) {
		this.id = p_Id;
		System.out.println(this.id);
		this.unit = null;
		this.setBackground(new Color(0,0,0,0));
		this.setOpaque(false);
		this.setBorder(null);
		this.setContentAreaFilled(false);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		neighbors = new ArrayList<Hexagon>();
		addMouseListener(this);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean isObstacle() {
		return isObstacle;
	}

	public void setObstacle(boolean obstacle) {
		isObstacle = obstacle;
	}

	public void setNeighbors(ArrayList<Hexagon> arrayList){
		neighbors = arrayList;
	}

	public void addNeighbor(Hexagon hexagon){
		neighbors.add(hexagon);
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return texture;
	}

	public boolean isContainCapital() {
		return containCapital;
	}

	public void setContainCapital(boolean containCapital) {
		this.containCapital = containCapital;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("X : " + this.posX);
		System.out.println("Y : " + this.posY);
		System.out.println("Id : " + this.id);
		System.out.println("Voisin début");

		for(int i = 0; i<this.getNeighbors().size(); i++){
			System.out.println(this.getNeighbors().get(i).getId());
		}

		System.out.println("Voisin fin");
		if(this.unit != null)
			System.out.println("Il y a déjà une unité sur cet héxagone !");
		else {
			if(Interface.wantPlaceUnit() && Capital.getCurrentGold() >= Unit.getGoldCost()) {
				System.out.println("Ajout d'une nouvelle unité.");
				this.unit = new Unit();
				Capital.setCurrentGold(Capital.getCurrentGold() - Unit.getGoldCost());
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public ArrayList<Hexagon> getNeighbors(){
		return neighbors;
	}
}
