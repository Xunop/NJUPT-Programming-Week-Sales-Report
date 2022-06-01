package com.reportform.controller;

import com.reportform.entity.Order;
import com.reportform.entity.ProductOrder;
import com.reportform.exception.LocalRunTimeException;
import com.reportform.service.ProService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author xun
 * @create 2022/5/27 22:17
 */
@Slf4j
@Controller

public class SearchController {

    private static final Integer LIMIT = 10;
    final ProService proService;

    public SearchController(ProService proService) {
        this.proService = proService;
    }

    @RequestMapping("/search")
    public String search(
            @RequestParam(value = "zone", required = false) String zone,
            @RequestParam(value = "pro_name", required = false) String proName,
            @RequestParam(value = "plat_name", required = false) String platName,
            @RequestParam(value = "order_id", required = false) Integer orderId,
            @RequestParam(value = "time", required = false) String time,
            @RequestParam(value = "page_num", required = false, defaultValue = "1") Integer pageNum,
            Model model) {
        if (Objects.equals(zone, "")) zone = null;
        int totalPage = Math.round(proService.TotalPage(zone, proName, platName, orderId, time) / 10);
        if (totalPage == 0) totalPage = 1;
        List<Order> orders = proService.search(zone, proName, platName, orderId, time, 10, pageNum);
        model.addAttribute("orders", orders);
        model.addAttribute("zone",zone);
        model.addAttribute("pro_name",proName);
        model.addAttribute("plat_name",platName);
        model.addAttribute("order_id",orderId);
        model.addAttribute("time",time);
        model.addAttribute("page_num",pageNum);
        model.addAttribute("total_page", totalPage);
        return "index";
    }
}
