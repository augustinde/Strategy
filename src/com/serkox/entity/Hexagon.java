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
	private Texture textureBase;
	private boolean containCapital;
	private Unit unit;
	private ArrayList<Hexagon> neighbors;
	private Capital capital;

	private boolean view;
	private int distance = 0;

	/**
	 * Détermine si l'hexagon est cliqué ou non
	 */
	private boolean active;

	public Hexagon(int p_Id) {
		this.view = false;
		this.id = p_Id;
		this.active = false;
		//System.out.println(this.id);
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

	public boolean checkNearbyCapital(){

		boolean capitalNearby = false;

		for (Hexagon neighbor : this.neighbors){
			if(neighbor.isContainCapital()){
				capitalNearby = true;
				//System.out.println("CAPITAAAAL");
			}
		}
		return capitalNearby;
	}

	public void placeUnit(){

		if(this.checkNearbyCapital()){
			System.out.println("Ajout d'une nouvelle unité.");
			Interface.setMessage("Unité placé !");
			this.unit = new Unit(this);
			Interface.setWantPlaceUnit(false);
			Grid.getCapitalJoueur().setCurrentGold(Grid.getCapitalJoueur().getCurrentGold() - Unit.getGoldCost());
		}else{
			System.out.println("Impossible de placer une unité loin de la capital !");
			Interface.setMessage("Impossible de placer une unité loin de la capital !");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Id : " + this.id + " X : " + this.posX + " Y : " + this.posY);

		//Placement unité
		if (Interface.wantPlaceUnit() && Grid.getCapitalJoueur().getCurrentGold() >= Unit.getGoldCost()) {
			if (this.unit != null){
				System.out.println("Il y a déjà une unité sur cet hexagone !");
				Interface.setMessage("Il y a déjà une unité sur cet hexagone !");
			}else {
				//Poser une unité
				this.placeUnit();
			}

		}else{

			if (this.unit != null){

				//L'unité ne veut pas encore se déplacer
				if(!this.unit.isWantMove()) {

					//L'unite veut se déplacer
					this.unit.setWantMove(true);
					Grid.getCapitalJoueur().setUnitToDeplace(this.unit);
					System.out.println(Grid.getCapitalJoueur().getUnitToDeplace());

					System.out.println("Le joueur souhaite déplacer une unité !");
					Interface.setMessage("Le joueur souhaite déplacer une unité !");
					Grid.getCapitalJoueur().getUnitToDeplace().calculDistance();

				//L'unité veut
				}else{

					if(this.unit == Grid.getCapitalIa().getUnitToDeplace()){
						this.unit.setWantMove(false);
						System.out.println("Le joueur ne souhaite plus déplacer d' unité !");
						Interface.setMessage("Le joueur ne souhaite plus déplacer d'unité !");
						Grid.getCapitalJoueur().setUnitToDeplace(null);
					}else{
						System.out.println("Il y à déjà une unité sur cet hexagon !");
					}
				}

			}else{

				System.out.println("Unité : " + Grid.getCapitalJoueur().getUnitToDeplace());

				if (Grid.getCapitalJoueur().getUnitToDeplace() != null && !this.isContainCapital()) {

					Hexagon startHexagon = Grid.getCapitalJoueur().getUnitToDeplace().getHexagon();
					Interface.setMessage("Id de la destination : " + this.getId());
					Grid.getCapitalJoueur().getUnitToDeplace().moveToDestination(this);
					//System.out.println("Fin du déplacement  !");
					//Interface.setMessage("Fin du déplacement !");
				}

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

		if(Grid.getCapitalJoueur().getUnitToDeplace() != null && Grid.getCapitalJoueur().getUnitToDeplace().isWantMove() && this.getUnit() == null && !this.isContainCapital()){
			Grid.getCapitalJoueur().getUnitToDeplace().path(this);
		}
		/*for (Hexagon neighbor : this.neighbors){

			neighbor.setTexture(new Texture("voisin"));

		}
		*/
	}

	@Override
	public void mouseExited(MouseEvent e) {
		/*for (Hexagon neighbor : this.neighbors){

			neighbor.setTexture(new Texture("grass"));

		}
		*/
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

	public Capital getCapital() {
		return capital;
	}

	public void setCapital(Capital capital) {
		this.capital = capital;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Texture getTextureBase() {
		return textureBase;
	}

	public void setTextureBase(Texture textureBase) {
		this.textureBase = textureBase;
	}

	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
