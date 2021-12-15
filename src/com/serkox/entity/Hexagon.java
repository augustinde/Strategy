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
	private Capital capital;
	private boolean active;

	public Hexagon(int p_Id) {
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

		boolean r = false;

		for (Hexagon neighbor : this.neighbors){
			if(neighbor.isContainCapital()){
				r = true;
				System.out.println("CAPITAAAAL");
			}else{
				System.out.println("PAS CAPITAL");
			}
		}
		return r;
	}

	public void placeUnit(){

		if(this.checkNearbyCapital()){
			System.out.println("Ajout d'une nouvelle unité.");
			Interface.setMessage("Unité placé !");
			this.unit = new Unit(this);
			Grid.getCapitalJoueur().setCurrentGold(Grid.getCapitalJoueur().getCurrentGold() - Unit.getGoldCost());
		}else{
			System.out.println("Impossible de placer une unité loin de la capital !");
			Interface.setMessage("Impossible de placer une unité loin de la capital !");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("X : " + this.posX);
		System.out.println("Y : " + this.posY);
		System.out.println("Id : " + this.id);

		/*System.out.println("Voisin début");
		for(int i = 0; i<this.getNeighbors().size(); i++){
			System.out.println(this.getNeighbors().get(i).getId());
		}
		System.out.println("Voisin fin");*/

		//Placement unité
		if (Interface.wantPlaceUnit() && Grid.getCapitalJoueur().getCurrentGold() >= Unit.getGoldCost()) {
			if (this.unit != null){
				System.out.println("Il y a déjà une unité sur cet hexagone !");
				Interface.setMessage("Il y a déjà une unité sur cet hexagone !");
			}else {
				//Poser une unité
				this.placeUnit();
			}

		} else {

			if (this.unit != null){

				if(!Interface.isWantDeplace()) {

					// if(!this.active) {

						//L'unite veut se déplacer
						//this.unit.setWantMove(true);
						//this.active = true;
						Interface.setWantDeplace(true);
						Interface.setUnitWantDeplace(this.unit);

						System.out.println("Le joueur souhaite déplacer une unité !");
						Interface.setMessage("Le joueur souhaite déplacer une unité !");
						/*for (Hexagon neighbor : this.neighbors) {

							if (neighbor.isContainCapital()) {

								if (neighbor.getCapital().getId() == 1) {
									neighbor.setTexture(new Texture("voisin_joueur"));
								} else {
									neighbor.setTexture(new Texture("voisin_ia"));
								}

							} else {
								neighbor.setTexture(new Texture("voisin"));

							}

						}*/

					//}

				}else{

					//L'unité ne veut plus se déplacer
					//this.active = false;
					//this.unit.setWantMove(false);
					Interface.setWantDeplace(false);
					System.out.println("Le joueur ne souhaite plus déplacer d' unité !");
					Interface.setMessage("Le joueur ne souhaite plus déplacer d'unité !");
					Interface.setUnitWantDeplace(null);
					/*for (Hexagon neighbor : this.neighbors) {

						if (neighbor.isContainCapital()) {
							if (neighbor.getCapital().getId() == 1) {
								neighbor.setTexture(new Texture("capital_joueur"));
							} else {
								neighbor.setTexture(new Texture("capital_ia"));
							}
						} else {
							neighbor.setTexture(new Texture("grass"));

						}
					}*/
				}
			}else {
				if (Interface.isWantDeplace() && !this.isContainCapital()) {
					Hexagon lastHexagon = Interface.getUnitWantDeplace().getHexagon();
					Interface.setMessage("Id de la destination : " + this.getId());
					Interface.getUnitWantDeplace().move(this);
					this.setUnit(Interface.getUnitWantDeplace());
					lastHexagon.setUnit(null);
					Interface.setWantDeplace(false);
					Interface.setUnitWantDeplace(null);
					System.out.println("Fin du déplacement  !");
					Interface.setMessage("Fin du déplacement !");
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
}
