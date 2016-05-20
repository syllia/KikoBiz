package bj.kiko.projects.kikobiz.Rest;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.ObjectifyService;

import bj.kiko.projects.kikobiz.Services.SousCategorieService;
import bj.kiko.projects.kikobiz.model.SousCategorie;
import bj.kiko.projects.kikobiz.model.app.SousCategorieApp;
@Api(name = "monapi")
public class SousCategorieController {
	static {
		 ObjectifyService.register(SousCategorie.class);
	}
		@ApiMethod(
			path = "souscategories/{idCategorie}",
			httpMethod = ApiMethod.HttpMethod.GET)
		public List <SousCategorie>SouscategoriesList(@Named("idCategorie") String iD) {
			System.out.println(iD);
			// Récupère toute les Souscategories d'une categorie...
			List<SousCategorie> souscategories = SousCategorieService.findAllByCategorie(iD);
			return souscategories ;
		}
		@ApiMethod(
			name = "souscategorie.insert",
			path = "souscategories",
			httpMethod = ApiMethod.HttpMethod.POST		        
			)
		public SousCategorie save(SousCategorieApp sousCategorie) {
			System.out.println(sousCategorie.getCategorie());
			return SousCategorieService.save(sousCategorie);
		}
		@ApiMethod(
		    path = "souscategories/{idCategorie}/{idSousCategorie}/{newName}",
		    httpMethod = ApiMethod.HttpMethod.PUT
		    )
		public SousCategorie modifyID(@Named("idCategorie") String idCategorie, @Named("idSousCategorie") long idSousCategorie,
				@Named("newName") String newName) {
		    
		    return SousCategorieService.modifyById(idCategorie, idSousCategorie, newName);
		}
		@ApiMethod(
			    path = "souscategories/{idCategorie}/{idSousCategorie}/",
			    httpMethod = ApiMethod.HttpMethod.DELETE
			    )
			public SousCategorie deleteById(@Named("idCategorie") String idCategorie,
					@Named("idSousCategorie") Long idSousCategorie) {
			    
			    return SousCategorieService.deleteById(idCategorie, idSousCategorie);
			}
}
