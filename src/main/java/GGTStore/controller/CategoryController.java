package GGTStore.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import GGTStore.dto.CategoryDto;
import GGTStore.entity.Category;
import GGTStore.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDto());

		return "/assets/admin/product/_category";
	}

	@PostMapping("/category/saveOrUpdate")
	public String saveOrUpdate(Model model, @RequestParam("name") String name,
			@ModelAttribute("category") CategoryDto dto) {
		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);

		categoryService.save(entity);
		System.out.println("ok");

		model.addAttribute("message", "Lưu danh mục thành công");

		return "/assets/admin/product/_category";
	}

}
