package bj.kiko.projects.kikobiz.Services;

import java.util.List;

import bj.kiko.projects.kikobiz.model.Offre;
import bj.kiko.projects.kikobiz.repositories.OffreRepository;

public class OffreService {
	
	public static List<Offre> findAll() {
		return OffreRepository.findAll();
	}
	 public static void save(Offre offre) {
		OffreRepository.save(offre);
	}
}
