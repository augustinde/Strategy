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
	private int priority;

	private int distancePlayer;
	private boolean viewPlayer;

	/*

		arrayDistance[0] = distance pour le joueur
		arrayDistance[1] = distance pour l'ia
		arrayDistance[2] = distance pour le radius (uniquement pour capital)
		arrayDistance[3] = distance pour par rapport a l'hexagon prioritaire

	 */
	private final int[] arrayDistance = new int[4];

	/*

		arrayView[0] = vue pour le joueur
		arrayView[1] = vue pour l'ia
		arrayView[2] = vue pour le radius (uniquement pour capital)
		arrayView[3] = vue pour l'hexagon prioritaire

	 */
	private final boolean[] arrayView = new boolean[4];

	private int distanceBetweenCapitalJoueur = 0;
	private int distanceBetweenCapitalIa = 0;

	/**
	 * Détermine si l'hexagon est cliqué ou non
	 */
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
		this.priority = 10;
		this.viewPlayer = false;
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
			if (neighbor.isContainCapital()) {
				capitalNearby = true;
				//System.out.println("CAPITAAAAL");
				break;
			}
		}
		return capitalNearby;
	}

	public void placeUnit(){

		//if(this.checkNearbyCapital()){
		if(true){
			System.out.println("Ajout d'une nouvelle unité.");
			Interface.setMessage("Unité placé !");
			this.unit = new Unit(this);
			Grid.getCapitalJoueur().setCurrentGold(Grid.getCapitalJoueur().getCurrentGold() - Unit.getGoldCost());
			Grid.getCapitalJoueur().addUnit(this.unit);
			Interface.setWantPlaceUnit(false);

		}else{
			System.out.println("Impossible de placer une unité loin de la capital !");
			Interface.setMessage("Impossible de placer une unité loin de la capital !");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Id : " + this.id + " X : " + this.posX + " Y : " + this.posY);

		//Placement unité
		System.out.println("interface : " + Interface.wantPlaceUnit());

		if (Interface.wantPlaceUnit() && Grid.getCapitalJoueur().getCurrentGold() >= Player.getUnitGoldCost()) {
			if (this.unit != null){
				System.out.println("Il y a déjà une unité sur cet hexagone !");
				Interface.setMessage("Il y a déjà une unité sur cet hexagone !");
			}else {
				//Poser une unité
				this.placeUnit();
			}

		}else{

			if (this.unit != null && Grid.getCapitalJoueur().getUnitToDeplace() == null && Grid.getCapitalJoueur().getUnitCollection().contains(this.unit)){

				//Clique sur l'unité pour dire qu'on veut la déplacer
				if(!this.unit.isWantMove()) {

					//L'unite veut se déplacer
					this.unit.setWantMove(true);
					Grid.getCapitalJoueur().setUnitToDeplace(this.unit);
					System.out.println(Grid.getCapitalJoueur().getUnitToDeplace());

					System.out.println("Le joueur souhaite déplacer une unité !");
					Interface.setMessage("Le joueur souhaite déplacer une unité !");
					Grid.getCapitalJoueur().getUnitToDeplace().calculDistance();

				}else{

					//Ne souhaite plus déplacer l'unité
					if(this.unit == Grid.getCapitalJoueur().getUnitToDeplace()){
						this.unit.setWantMove(false);
						System.out.println("Le joueur ne souhaite plus déplacer d' unité !");
						Interface.setMessage("Le joueur ne souhaite plus déplacer d'unité !");
						Grid.getCapitalJoueur().setUnitToDeplace(null);
					}else{
						System.out.println("Il y à déjà une unité sur cet hexagon !");
					}

				}

			}else{

				//Clique sur l'hexagone de destination => déplacement
				if (Grid.getCapitalJoueur().getUnitToDeplace() != null && !this.isContainCapital()) {

					Grid.getCapitalJoueur().getUnitToDeplace().setDeplace(true);
					Grid.getCapitalJoueur().getUnitToDeplace().moveToDestination(this);

					System.out.println("Fin du déplacement  !");
					Interface.setMessage("Fin du déplacement !");
					Grid.resetTextureHexagons();
					Grid.resetViewHexagons();

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
			if(!Grid.getCapitalJoueur().getUnitToDeplace().isDeplace()){
				Grid.getCapitalJoueur().getUnitToDeplace().path(this);
			}
		}

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

	public int getDistanceBetweenCapitalIa() {
		return distanceBetweenCapitalIa;
	}

	public void setDistanceBetweenCapitalIa(int distanceBetweenCapitalIa) {
		this.distanceBetweenCapitalIa = distanceBetweenCapitalIa;
	}

	public int getDistanceBetweenCapitalJoueur() {
		return distanceBetweenCapitalJoueur;
	}

	public void setDistanceBetweenCapitalJoueur(int distanceBetweenCapitalJoueur) {
		this.distanceBetweenCapitalJoueur = distanceBetweenCapitalJoueur;
	}

	public int[] getArrayDistance() {
		return arrayDistance;
	}

	public boolean[] getArrayView() {
		return arrayView;
	}

	/*public int getDistancePlayer(){
		return this.arrayDistance[0];
	}*/

	public int getDistanceIa(){
		return this.arrayDistance[1];
	}

	public int getDistanceRadius(){
		return this.arrayDistance[2];
	}

	public int getDistancePriorityHexagon(){
		return this.arrayDistance[3];
	}

	/*public boolean getViewPlayer(){
		return this.arrayView[0];
	}*/

	public boolean getViewIa(){
		return this.arrayView[1];
	}

	public boolean getViewRadius(){
		return this.arrayView[2];
	}

	public boolean getViewPriorityHexagon(){
		return this.arrayView[3];
	}

	/*public void setViewPlayer(boolean param){
		this.arrayView[0] = param;
	}*/

	public void setViewIa(boolean param){
		this.arrayView[1] = param;
	}

	public void setViewRadius(boolean param){
		this.arrayView[2] = param;
	}

	public void setViewPriorityHexagon(boolean param){
		this.arrayView[3] = param;
	}

	/*public void setDistancePlayer(int param){
		this.arrayDistance[0] = param;
	}*/

	public void setDistanceIa(int param){
		this.arrayDistance[1] = param;
	}

	public void setDistanceRadius(int param){
		this.arrayDistance[2] = param;
	}

	public void setDistancePriorityHexagon(int param){
		this.arrayDistance[3] = param;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getDistancePlayer() {
		return distancePlayer;
	}

	public void setDistancePlayer(int distancePlayer) {
		this.distancePlayer = distancePlayer;
	}

	public boolean getViewPlayer() {
		return this.viewPlayer;
	}

	public void setViewPlayer(boolean viewPlayer) {
		this.viewPlayer = viewPlayer;
	}
}
