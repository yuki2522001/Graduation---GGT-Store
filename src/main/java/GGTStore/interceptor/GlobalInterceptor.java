package GGTStore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import GGTStore.service.CategoryService;
import GGTStore.service.FlashsaleService;
import GGTStore.service.ProducerService;
import GGTStore.service.ProductService;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	CategoryService categoryService;
	@Autowired
	FlashsaleService flashsaleService;
	@Autowired
	ProducerService producerService;
	@Autowired
	ProductService productService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("cates", categoryService.findAll());
		request.setAttribute("flashs", flashsaleService.findAll());
		request.setAttribute("producers", producerService.findAll());
		request.setAttribute("productquantity", productService.findtByProductquantitysold(null));
		// request.setAttribute("comment", commentService.findAll());
	}
}
