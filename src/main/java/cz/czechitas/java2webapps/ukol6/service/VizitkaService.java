package cz.czechitas.java2webapps.ukol6.service;

import cz.czechitas.java2webapps.ukol6.entity.Vizitka;
import cz.czechitas.java2webapps.ukol6.repository.VizitkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VizitkaService {

    private final VizitkaRepository vizitkaRepository;

    @Autowired
    public VizitkaService(VizitkaRepository vizitkaRepository) {

        this.vizitkaRepository = vizitkaRepository;
    }

    public Page<Vizitka> seznamVizitek(Pageable pageable) {
        return vizitkaRepository.findAll(pageable);
    }

    public Optional<Vizitka> vizitkaDleId(int id) {
        return vizitkaRepository.findById(id);
    }

    public void pridatVizitku(Vizitka vizitka) {
        vizitkaRepository.save(vizitka);
    }

    public void smazatPodleId(int id) {
        Vizitka vizitka = vizitkaDleId(id).get();
        vizitkaRepository.delete(vizitka);
    }

}
