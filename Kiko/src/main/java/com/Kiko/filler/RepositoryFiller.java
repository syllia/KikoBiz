package com.Kiko.filler;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.Kiko.model.Category;
import com.Kiko.model.City;
import com.Kiko.model.Country;
import com.Kiko.model.ImageOffer;
import com.Kiko.model.Offer;
import com.Kiko.model.SessionIdentifierGenerator;
import com.Kiko.model.SubCategory;
import com.Kiko.model.User;
import com.Kiko.repositories.CategoryRepository;
import com.Kiko.repositories.CityRepository;
import com.Kiko.repositories.CountryRepository;
import com.Kiko.repositories.ImageOfferRepository;
import com.Kiko.repositories.OfferRepository;
import com.Kiko.repositories.SubCategoryRepository;
import com.Kiko.repositories.UserRepository;

@Component
public class RepositoryFiller {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private ImageOfferRepository imageOfferRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CityRepository cityRepository;
	@Bean
	CommandLineRunner runner() {
		return args -> {
			fillCategrorieRepository(categoryRepository);
			fillSousCategorieRepository(subCategoryRepository);
			fillOfferRepository(offerRepository);
			fillImageOfferRepository(imageOfferRepository);
			fillUserRepository(userRepository);
			fillCountryRepository(countryRepository);
			fillCityRepository(cityRepository);
		};
	}

	private void fillCityRepository(CityRepository cityRepository) {
		cityRepository.save(new City(229,"Calavi"));
		cityRepository.save(new City(229,"Cotonou"));
		cityRepository.save(new City(229,"Porto"));
		cityRepository.save(new City(229,"Parakou"));
		
	}

	private void fillCountryRepository(CountryRepository countryRepository) {
		countryRepository.save(new Country(229,"Bénin"));
		
	}

	private void fillUserRepository(UserRepository userRepository) {
		userRepository.save(new User(418));
		
	}

	private void fillImageOfferRepository(ImageOfferRepository imageOfferRepository) throws IOException {
		String workingDir = System.getProperty("user.dir");
		
		ImageOffer i1=new ImageOffer();
		i1.setIdOffer(1);
	
	
		String encoded = Base64.getEncoder().encodeToString(extractBytes(workingDir 
				+ "/src/main/java/com/Kiko/filler/iiii.jpg"));
		i1.setByteArray(encoded);
		imageOfferRepository.save(i1);
		
		ImageOffer i2=new ImageOffer();
		i2.setIdOffer(2);
		i2.setByteArray(encoded);
		imageOfferRepository.save(i2);
		
	}

	private void fillOfferRepository(OfferRepository offerRepository) {
		Offer offer = new Offer();
		offer.setCost(10);
		offer.setIdSousCategorie(1);
		offerRepository.save(offer);
		Offer offer1 = new Offer();
		offer1.setIdSousCategorie(1);
		offer1.setCost(5);
		offerRepository.save(offer1);
	}

	private void fillSousCategorieRepository(SubCategoryRepository subCategoryRepository) {
		subCategoryRepository.save(new SubCategory( 1, "Téléphones"));
		subCategoryRepository.save(new SubCategory( 1, "Ordinateur portables"));
		subCategoryRepository.save(new SubCategory( 1, "Ordinateur de bureau"));
		subCategoryRepository.save(new SubCategory(2, "Voitures et camions"));
		subCategoryRepository.save(new SubCategory( 2, "Motos"));
		subCategoryRepository.save(new SubCategory( 2, "Vélos"));
		subCategoryRepository.save(new SubCategory( 3, "Lits,matelas"));
		subCategoryRepository.save(new SubCategory( 3, "Chaises,fauteuils"));
		subCategoryRepository.save(new SubCategory(3, "Tables basses"));
		

	}

	private void fillCategrorieRepository(CategoryRepository categoryRepository) {
		categoryRepository.save(new Category("Informatique et multimedia"));
		categoryRepository.save(new Category("Autos et véhicules"));
		categoryRepository.save(new Category("Meubles et décoration"));
		categoryRepository.save(new Category("Livres"));
		categoryRepository.save(new Category("Animaux"));
		categoryRepository.save(new Category("Électroménager et vaisselles"));
		categoryRepository.save(new Category("Immobilier"));
		categoryRepository.save(new Category("Habillement et Bien-être"));
		categoryRepository.save(new Category("Objets gratuits"));
		

	}
	public byte[] extractBytes (String ImageName) throws IOException {
		 // open image
		 File imgPath = new File(ImageName);
		 BufferedImage bufferedImage = ImageIO.read(imgPath);

		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage .getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		 return ( data.getData() );
		}
}
