package com.kouri.ressources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouri.domain.OfferRepository;
import com.kouri.domain.PhotoRepository;

@Service
public class PhotoServices {
	@Autowired
	private PhotoRepository photoRepository;
	@Autowired
	private OfferRepository offerRepository;

}
