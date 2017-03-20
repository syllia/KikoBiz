package com.kouri.ressources;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoRessources {

	@RequestMapping(value = "/image", method = RequestMethod.GET, produces = { "image/png", "image/jpeg",
			"image/gif" }, headers = { "content-type=image/jpeg" })
	public ResponseEntity<?> findAll() {
		File file = new File(
				"/Users/julioadossou/Documents/KikoBiz/offer-service/src/main/resources/14691960_10206121873320366_6430347671553571458_o.jpg");

		return new ResponseEntity<>((Object) file, HttpStatus.OK);
		// responseBuilder.header("Content-Disposition", "attachment;
		// filename=\"MyPngImageFile.png\"");
		// return responseBuilder.build();
	}
}
