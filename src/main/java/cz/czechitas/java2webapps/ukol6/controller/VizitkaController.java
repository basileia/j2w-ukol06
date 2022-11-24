package cz.czechitas.java2webapps.ukol6.controller;

import cz.czechitas.java2webapps.ukol6.entity.Vizitka;
import cz.czechitas.java2webapps.ukol6.service.VizitkaService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class VizitkaController {

    private final VizitkaService service;

    public VizitkaController(VizitkaService service) {
        this.service = service;
    }

    // prázdné stringy se převedou na null
    @InitBinder
    public void nullStringBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public ModelAndView seznam(@PageableDefault(sort = {"celeJmeno"}) Pageable pageable) {
        return new ModelAndView("seznam")
                .addObject("seznam", service.seznamVizitek(pageable));
    }

    @GetMapping("/detail/{id}")
    public Object detail(@PathVariable int id) {
        Optional<Vizitka> hledanaVizitka = service.vizitkaDleId(id);

        if (!hledanaVizitka.isEmpty()) {
             return new ModelAndView("vizitka")
                    .addObject("vizitka", service.vizitkaDleId(id).get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nova")
    public ModelAndView novaVizitka() {
         return new ModelAndView("formular")
                .addObject("vizitka", new Vizitka());
    }

    @PostMapping("/nova")
    public String pridatVizitku(@Valid @ModelAttribute("vizitka") Vizitka vizitka, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/formular";
        }
        service.pridatVizitku(vizitka);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String odebratVizitku(int id) {
        service.smazatPodleId(id);
        return "redirect:/";
    }
}
