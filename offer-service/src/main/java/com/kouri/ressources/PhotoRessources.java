package com.kouri.ressources;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoRessources {

	@RequestMapping(value = "/image", method = RequestMethod.GET, produces = "image/jpg")
	public @ResponseBody byte[] getFile() {
		try {
			// Retrieve image from the classpath.
			InputStream is = new FileInputStream(
					"/Users/julioadossou/Documents/KikoBiz/offer-service/src/main/resources/14691960_10206121873320366_6430347671553571458_o.jpg");

			// Prepare buffered image.
			BufferedImage img = ImageIO.read(is);

			// Create a byte array output stream.
			ByteArrayOutputStream bao = new ByteArrayOutputStream();

			// Write to output stream
			ImageIO.write(img, "jpg", bao);

			return bao.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
