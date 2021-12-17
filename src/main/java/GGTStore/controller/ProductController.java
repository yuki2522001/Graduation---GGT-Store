package GGTStore.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import GGTStore.dao.CategoryDAO;
import GGTStore.dao.OrderDetailDAO;
import GGTStore.dao.ProductDAO;
import GGTStore.entity.Category;
import GGTStore.entity.FlashSale;
import GGTStore.entity.Producer;
import GGTStore.entity.Product;
import GGTStore.service.CategoryService;
import GGTStore.service.FlashsaleService;
import GGTStore.service.ProducerService;
import GGTStore.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	OrderDetailDAO OD;
	@Autowired
	ProductDAO pdao;
	@Autowired
	CategoryDAO cdao;
	@Autowired
	FlashsaleService flashsaleService;
	@Autowired
	ProducerService producerService;

	@RequestMapping("/product/searchprice")
	public String search(Model model, @RequestParam("min") Optional<Double> min,
			@RequestParam("product") Optional<Integer> product, @RequestParam("max") Optional<Double> max) {
		Pageable pageable = PageRequest.of(product.orElse(0), 9);
		double minPrice = min.orElse(Double.MIN_VALUE);
		double maxPrice = max.orElse(Double.MAX_VALUE);
		Page<Product> list = pdao.findByPrice(minPrice, maxPrice, pageable);
		model.addAttribute("productPage", list);
		return "product/search";
	}

	@RequestMapping("/product/page")
	public ModelAndView page(ModelMap model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("name") String name, @RequestParam("filter") Optional<Integer> filter,
			@RequestParam("brand") Long brand) {
		int currentPage = page.orElse(0);
		int filterPage = filter.orElse(0);

		Pageable pageable = PageRequest.of(currentPage, 9);

		if (brand != -1) {
			if (filterPage == 0) {
				pageable = PageRequest.of(currentPage, 9);
			} else if (filterPage == 1) {
				pageable = PageRequest.of(currentPage, 9, Sort.by(Sort.Direction.DESC, "createDate"));
			} else if (filterPage == 2) {
				pageable = PageRequest.of(currentPage, 9, Sort.by(Sort.Direction.ASC, "createDate"));
			} else if (filterPage == 3) {
				pageable = PageRequest.of(currentPage, 9, Sort.by(Sort.Direction.DESC, "price"));
			} else if (filterPage == 4) {
				pageable = PageRequest.of(currentPage, 9, Sort.by(Sort.Direction.ASC, "price"));
			}
		} else {
			if (filterPage == 0) {
				pageable = PageRequest.of(currentPage, 9);
			} else if (filterPage == 1) {
				pageable = PageRequest.of(currentPage, 9, Sort.by(Sort.Direction.DESC, "createDate"));
			} else if (filterPage == 2) {
				pageable = PageRequest.of(currentPage, 9, Sort.by(Sort.Direction.ASC, "createDate"));
			} else if (filterPage == 3) {
				pageable = PageRequest.of(currentPage, 9, Sort.by(Sort.Direction.DESC, "price"));
			} else if (filterPage == 4) {
				pageable = PageRequest.of(currentPage, 9, Sort.by(Sort.Direction.ASC, "price"));
			}
		}

		Page<Product> listP = null;
		if (brand == -1) {
			listP = pdao.findByNameContaining(name, pageable);
		} else {
			listP = pdao.findAllProductByCategoryId(brand, pageable);
		}

		int totalPage = listP.getTotalPages();
		if (totalPage > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPage);
			if (totalPage > 9) {
				if (end == totalPage) {
					start = end - 9;
				} else if (start == 1) {
					end = start + 9;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		List<Category> listC = cdao.findAll();
		model.addAttribute("categories", listC);
		model.addAttribute("brand", brand);
		model.addAttribute("filter", filterPage);
		model.addAttribute("name", name);
		model.addAttribute("productPage", listP);
		model.addAttribute("slide", true);
		return new ModelAndView("/product/search", model);
	}

	@GetMapping("/product/list")
	public String list(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			@RequestParam("cid") Optional<String> cid, @RequestParam("id") Optional<String> id) {

		int currentPage = page.orElse(1); // gia tri ngam dinh la 1
		int pageSize = size.orElse(9); // ngam dinh 9 phan tu tren 1 trang

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<Product> resultPage = null;
		if (cid.isPresent()) {
			resultPage = productService.findByCategory(cid.get(), pageable);
			model.addAttribute("productPage", resultPage);
		}else {
			resultPage = productService.findAll(pageable);
		}
		// tra ve tong so trang da duoc phan trang
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 9) {
				if (end == totalPages)
					start = end - 9;
				else if (start == 1)
					end = start + 9;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		return "product/list";
	}

	@GetMapping("/product/list/search")
	public String listSearch(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			@RequestParam("sid") Optional<String> sid) {

		int currentPage = page.orElse(1); // gia tri ngam dinh la 1
		int pageSize = size.orElse(9); // ngam dinh 9 phan tu tren 1 trang

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<Product> resultPage = null;
		if (sid.isPresent()) {
			resultPage = productService.findByCategory(sid.get(), pageable);
			model.addAttribute("productPage", resultPage);
		} else {
			resultPage = productService.findAll(pageable);
		}
		// tra ve tong so trang da duoc phan trang
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 9) {
				if (end == totalPages)
					start = end - 9;
				else if (start == 1)
					end = start + 9;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		return "product/search";
	}
	
	@GetMapping("/producer/list/search")
    public String listSearchProducer(ModelMap model, @RequestParam(name = "name", required = false) String name,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
            @RequestParam("pid") Optional<String> pid) {

        int currentPage = page.orElse(1); // gia tri ngam dinh la 1
        int pageSize = size.orElse(9); // ngam dinh 9 phan tu tren 1 trang

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Product> resultPage = null;
        if (pid.isPresent()) {
            resultPage = productService.findByProducer(pid.get(), pageable);
            model.addAttribute("productPage", resultPage);
        }
         else {
            resultPage = productService.findAll(pageable);
        }
        // tra ve tong so trang da duoc phan trang
        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > 9) {
                if (end == totalPages)
                    start = end - 9;
                else if (start == 1)
                    end = start + 9;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("productPage", resultPage);
        return "product/search";
    }
	
	@GetMapping("/flashsale/list/search")
	public String listFlashSaleSearch(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			@RequestParam("id") Optional<String> id) {

		int currentPage = page.orElse(1); // gia tri ngam dinh la 1
		int pageSize = size.orElse(9); // ngam dinh 9 phan tu tren 1 trang

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<FlashSale> resultPage = null;
		if (id.isPresent()) {
			resultPage = flashsaleService.findByCategory(id.get(), pageable);
			model.addAttribute("productPage", resultPage);
		} else {
			resultPage = flashsaleService.findAll(pageable);
		}
		// tra ve tong so trang da duoc phan trang
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 9) {
				if (end == totalPages)
					start = end - 9;
				else if (start == 1)
					end = start + 9;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		return "product/search";
	}

	@GetMapping("/product/search")
	public String search(Model model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("p") Optional<Integer> p) {
		Page<Product> list = null;
		Pageable pageable = PageRequest.of(p.orElse(0), 9);
		if (StringUtils.hasText(name)) {
			list = productService.findByNameContaining(name, pageable);
		} else {
			list = productService.findAll(pageable);
		}
		model.addAttribute("productPage", list);
		return "product/search";
	}

	@ModelAttribute("categories")
	public String listc(Model model, @RequestParam("cid") Optional<String> cid) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("cates", list);
		return "product/search";
	}

	@ModelAttribute("producers")
	public String listp(Model model, @RequestParam("id") Optional<String> id) {
		List<Producer> list = producerService.findAll();
		model.addAttribute("producers", list);
		return "product/search";
	}

	@ModelAttribute("productquantity")
	public String listps(Model model, @RequestParam("id") Optional<String> id) {
		Product list = productService.findtByProductquantitysold(id);
		model.addAttribute("productquantity", list);
		return "product/search";
	}

	@ModelAttribute("flashsales")
	public String listf(Model model, @RequestParam("fid") Optional<String> fid) {
		List<FlashSale> list = flashsaleService.findAll();
		model.addAttribute("flashs", list);
		return "product/search";
	}
	
	@ModelAttribute("TOP8")
	public String top8Product(Model model) {
		List<Product> list = productService.findTop8Product();
		model.addAttribute("TOP8", list);
		return "product/list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "product/detail";
	}


	@RequestMapping("/flashsale/detail/{id}")
	public String detailflash(Model model, @PathVariable("id") Integer id) {
		FlashSale item = flashsaleService.findById(id);
		model.addAttribute("item", item);
		return "product/detailflash";
	}
	
	@GetMapping("view/issurance")
    public String issurance() {
        return "product/issurance";
    }

	@GetMapping("view/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = (Pageable) PageRequest.of(p.orElse(0), 12);
		Page<Product> page = productService.findAllPro((Pageable) pageable);
		model.addAttribute("productPage", page);
		return "product/search";
	}
	
}
