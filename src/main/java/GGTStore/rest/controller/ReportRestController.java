package GGTStore.rest.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import GGTStore.dao.OrderDetailDAO;
import GGTStore.service.OrderDetailService;

@CrossOrigin("*")
@RestController
public class ReportRestController {
    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrderDetailDAO OD;

    @GetMapping("/rest/reportTotalProduct")
    public List<Object[]> getTotalProduct() {
        return orderDetailService.getTotalReport();
    }

    @GetMapping(value = "/rest/reportMonth")
    public Object getByStartDateBetween() {
        return OD.getAmount();
    }

    @GetMapping("/rest/reportLoaiHang")
    public List<Object[]> getloaiHang() {
        return OD.getloaihang();
    }

    @GetMapping(value = "/rest/date/{startDate}/{endDate}")
    public List<Object[]> getDate(@PathVariable Date startDate,@PathVariable Date endDate) {
        return OD.getDate(startDate, endDate);
    }
}
