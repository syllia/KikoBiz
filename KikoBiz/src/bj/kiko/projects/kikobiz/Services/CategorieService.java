package bj.kiko.projects.kikobiz.Services;

import java.util.List;

import bj.kiko.projects.kikobiz.model.Categorie;
import bj.kiko.projects.kikobiz.repositories.CategorieRepository;

public class CategorieService {
	public static List<Categorie> findAll() {
		return CategorieRepository.findAll();
	}
	 public static Categorie save(Categorie Categorie) {
		return CategorieRepository.save(Categorie);
	}
	 public static Categorie modifyId(String id,String nvId){
		 return CategorieRepository.modifyId(id, nvId);
	 }
	 public static  Categorie deleteById(String id){
		 return CategorieRepository.deleteById(id);
	 }
}
