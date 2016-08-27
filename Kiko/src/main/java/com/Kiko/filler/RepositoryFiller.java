package com.Kiko.filler;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.Kiko.model.Category;
import com.Kiko.model.ImageOffer;
import com.Kiko.model.Offer;
import com.Kiko.model.SubCategory;
import com.Kiko.repositories.CategoryRepository;
import com.Kiko.repositories.ImageOfferRepository;
import com.Kiko.repositories.OfferRepository;
import com.Kiko.repositories.SubCategoryRepository;

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
	@Bean
	CommandLineRunner runner() {
		return args -> {
			fillCategrorieRepository(categoryRepository);
			fillSousCategorieRepository(subCategoryRepository);
			fillOfferRepository(offerRepository);
			fillImageOfferRepository(imageOfferRepository);
		};
	}

	private void fillImageOfferRepository(ImageOfferRepository imageOfferRepository) throws IOException {
		String workingDir = System.getProperty("user.dir");
		
		ImageOffer i1=new ImageOffer();
		i1.setIdImageOffer(1);
		i1.setIdOffer(10);
		i1.setByteArray(extractBytes(workingDir 
				+ "/src/main/java/com/Kiko/filler/iiii.jpg"));
		imageOfferRepository.save(i1);
		
	}

	private void fillOfferRepository(OfferRepository offerRepository) {
		Offer offer = new Offer();
		offer.setCost(10);
		offer.setIdSousCategorie(1);
		offer.setOfferId(10);
		offerRepository.save(offer);
		Offer offer1 = new Offer();
		offer1.setIdSousCategorie(2);
		offer1.setOfferId(12);
		offerRepository.save(offer1);
		
	}

	private void fillSousCategorieRepository(SubCategoryRepository subCategoryRepository) {
		subCategoryRepository.save(new SubCategory(1, 1, "Téléphones"));
		subCategoryRepository.save(new SubCategory(2, 1, "Ordinateur portables"));
		subCategoryRepository.save(new SubCategory(3, 1, "Ordinateur de bureau"));
		subCategoryRepository.save(new SubCategory(4, 2, "Voitures et camions"));
		subCategoryRepository.save(new SubCategory(5, 2, "Motos"));
		subCategoryRepository.save(new SubCategory(6, 2, "Vélos"));
		subCategoryRepository.save(new SubCategory(7, 3, "Lits,matelas"));
		subCategoryRepository.save(new SubCategory(8, 3, "Chaises,fauteuils"));
		subCategoryRepository.save(new SubCategory(9, 3, "Tables basses"));
		

	}

	private void fillCategrorieRepository(CategoryRepository categoryRepository) {
		categoryRepository.save(new Category(1, "Informatique et multimedia"));
		categoryRepository.save(new Category(2, "Autos et véhicules"));
		categoryRepository.save(new Category(3, "Meubles et décoration"));
		categoryRepository.save(new Category(4, "Livres"));
		categoryRepository.save(new Category(5, "Animaux"));
		categoryRepository.save(new Category(6, "Électroménager et vaisselles"));
		categoryRepository.save(new Category(7, "Immobilier"));
		categoryRepository.save(new Category(8, "Habillement et Bien-être"));
		categoryRepository.save(new Category(9, "Objets gratuits"));
		

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
