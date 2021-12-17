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

import GGTStore.entity.Producer;
import GGTStore.service.ProducerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/producers")
public class ProducerRestController {

    @Autowired
    ProducerService producerService;

    @GetMapping()
    public List<Producer> getAll() {
        return producerService.findAll();
    }

    @GetMapping("{id}")
    public Producer getOne(@PathVariable("id") String id) {
        return producerService.findById(id);
    }

    @PostMapping()
    public Producer create(@RequestBody Producer producer) {
        return producerService.create(producer);
    }

    @PutMapping("{id}")
    public Producer update(@RequestBody Producer producer, @PathVariable("id") String id) {
        return producerService.update(producer);
    }

    @DeleteMapping("{id}")
    public void update(@PathVariable("id") String id) {
        producerService.delete(id);
    }
}
