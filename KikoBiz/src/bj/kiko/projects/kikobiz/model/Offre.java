package bj.kiko.projects.kikobiz.model;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Cache()
public class Offre {
	@Id Long id;
	@Parent Key sousCategorie;
	@Index String typeAnnonce;
	@Index Date date;
	@Index Date dateFin;
	String titreAnnonce;
	@Index int prix;
	String description;
	int nbVues;
	
	List<String> Photos;

	public Offre(){
		
	}
	public Offre(Key sousCategorie,String typeAnnonce,Date datedebut,Date dateFin,
			String titreAnnonce,int prix,String description,int nbVues,List<String> photos){
		this.sousCategorie=sousCategorie;
		this.typeAnnonce=typeAnnonce;
		this.date=datedebut;
		this.dateFin=dateFin;
		this.titreAnnonce=titreAnnonce;
		this.prix=prix;
		this.description=description;
		this.nbVues=nbVues;
		this.Photos=photos;
		
	}
	public Key getSousCategorie() {
		return sousCategorie;
	}
	public void setSousCategorie(Key sousCategorie) {
		this.sousCategorie = sousCategorie;
	}
	public String getTypeAnnonce() {
		return typeAnnonce;
	}
	public void setTypeAnnonce(String typeAnnonce) {
		this.typeAnnonce = typeAnnonce;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public String getTitreAnnonce() {
		return titreAnnonce;
	}
	public void setTitreAnnonce(String titreAnnonce) {
		this.titreAnnonce = titreAnnonce;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNbVues() {
		return nbVues;
	}
	public void setNbVues(int nbVues) {
		this.nbVues = nbVues;
	}
	public List<String> getPhotos() {
		return Photos;
	}
	public void setPhotos(List<String> photos) {
		Photos = photos;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
