package bj.kiko.projects.kikobiz.Services;

import java.util.List;

import bj.kiko.projects.kikobiz.model.SousCategorie;
import bj.kiko.projects.kikobiz.model.app.SousCategorieApp;
import bj.kiko.projects.kikobiz.repositories.SousCategorieRepository;

public class SousCategorieService {
	public static List<SousCategorieApp> findAllByCategorie(String categorie) {
		return SousCategorieRepository.findAllByCategorie(categorie);
	}
	 public static SousCategorie save(SousCategorieApp sousCategorieApp) {
		 System.out.println(sousCategorieApp.getmName());
		return SousCategorieRepository.save(sousCategorieApp);
	}
	 public static SousCategorie modifyById(String idCategorie,long idSousCategorie,String newName){
		 return SousCategorieRepository.modifyById(idCategorie, idSousCategorie, newName);
	 }
	 public static  SousCategorie deleteById(String idCategorie,Long idSousCategorie){
		 return SousCategorieRepository.deleteById(idCategorie,idSousCategorie);
	 }
}
