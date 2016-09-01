package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.ImageOffer;
import com.Kiko.repositories.ImageOfferRepository;
@Service
public class ImageOfferService {
	private ImageOfferRepository imageOfferRepository;
	@Autowired 
	public ImageOfferService(ImageOfferRepository imageOfferRepository){
		this.imageOfferRepository = imageOfferRepository;
	}
	
	public byte[] getOne(int id) {
		return imageOfferRepository.getOne(id).getImgOffer();
	}
	public List<ImageOffer> save(List<ImageOffer> listeImageOffers) {
		return imageOfferRepository.save(listeImageOffers);
	}

}
