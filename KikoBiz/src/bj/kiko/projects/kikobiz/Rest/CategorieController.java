package bj.kiko.projects.kikobiz.Rest;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.ObjectifyService;

import bj.kiko.projects.kikobiz.Services.CategorieService;
import bj.kiko.projects.kikobiz.model.Categorie;

@Api(name = "monapi")
public class CategorieController {
	static {
		ObjectifyService.register(Categorie.class);
	}

	@ApiMethod(path = "categories", httpMethod = ApiMethod.HttpMethod.GET)
	public List<Categorie> categoriesList() {
		// Récupère toute les categories...
		List<Categorie> categories = CategorieService.findAll();
		return categories;
	}

	@ApiMethod(name = "categorie.insert", path = "categories", httpMethod = ApiMethod.HttpMethod.POST)
	public Categorie save(Categorie categorie) {
		return CategorieService.save(categorie);
	}

	@ApiMethod(path = "categorie/{ancienId}/{nvId}", httpMethod = ApiMethod.HttpMethod.PUT)
	public Categorie modifyID(@Named("ancienId") String ancienId, @Named("nvId") String nvId) {

		return CategorieService.modifyId(ancienId, nvId);
	}

	@ApiMethod(path = "categorie/{ancienId}", httpMethod = ApiMethod.HttpMethod.DELETE)
	public Categorie deleteById(@Named("ancienId") String ancienId) {

		return CategorieService.deleteById(ancienId);
	}

	//////////////////////////////////////////////////////////////////////////////////

}
