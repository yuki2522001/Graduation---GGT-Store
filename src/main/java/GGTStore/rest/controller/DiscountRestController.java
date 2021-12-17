package GGTStore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GGTStore.entity.Discount;
import GGTStore.service.DiscountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/percents")
public class DiscountRestController {
    @Autowired
    DiscountService discountService;

    @GetMapping()
    public List<Discount> getAll() {
        return discountService.findAll();
    }

    @GetMapping("{id}")
    public Discount getOne(@PathVariable("id") String id) {
        return discountService.findById(id);
    }

    @PostMapping()
    public Discount create(@RequestBody Discount Discount) {
        return discountService.create(Discount);
    }

    @PutMapping("{id}")
    public Discount update(@RequestBody Discount Discount, @PathVariable("id") String id) {
        return discountService.update(Discount);
    }

    @DeleteMapping("{id}")
    public void update(@PathVariable("id") String id) {
        discountService.delete(id);
    }
}
