package at.adesso.api.controller;

import at.adesso.api.model.Turbine;
import at.adesso.api.repository.TurbineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TurbineController {

    @Autowired
    private TurbineRepository turbineRepository;

    @GetMapping("/turbines")
    public List<Turbine> getTurbines() {
        return turbineRepository.findAll();
    }

}
