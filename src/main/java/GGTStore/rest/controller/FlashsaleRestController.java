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

import GGTStore.entity.FlashSale;
import GGTStore.service.FlashsaleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/flashsales")
public class FlashsaleRestController {

	@Autowired
	FlashsaleService flashsaleService;
	
	@GetMapping()
	public List<FlashSale> getAll() {
		return flashsaleService.findAll();
	}
	
	@GetMapping("{id}")
	public FlashSale getOne(@PathVariable("id") Integer id) {
		return flashsaleService.findById(id);
	}
	
	@PostMapping()
	public FlashSale create(@RequestBody FlashSale flashsale) {
		return flashsaleService.create(flashsale);
	}
	
	@PutMapping("{id}")
	public FlashSale update(@RequestBody FlashSale flashsale, @PathVariable("id") Integer id) {
		return flashsaleService.update(flashsale);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		flashsaleService.delete(id);
	}
}
