package com.reportform.controller;

import com.reportform.entity.Order;
import com.reportform.pojo.SecondSmoothingEntity;
import com.reportform.pojo.VendSummary;
import com.reportform.service.ProService;
import com.reportform.util.DataForecast;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xun
 * @create 2022/5/27 15:28
 */
@Controller
public class ProController {

    final ProService proService;

    public ProController(ProService proService) {
        this.proService = proService;
    }

    @PostMapping ("/add")
    public String add(@RequestParam("pro_name" ) String proName,
                      @RequestParam("plat_name") String platName,
                      @RequestParam("zone") String zone) {
        proService.add(proName, platName, zone);
        return "addorder";
    }

    @RequestMapping("/addo")
    public String addo(){
        return "addorder";
    }


    @PostMapping("/update")
    public String update(@RequestParam("order_id") Integer orderId,
                         @RequestParam("zone") String zone,
                         @RequestParam("pro_name") String proName) {
        proService.update(orderId, zone, proName);
        return "update";
    }

    @RequestMapping("/updateo")
    public String updateo(@RequestParam(value = "order_id", required = false) Integer orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "update";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("order_id") Integer orderId) {
        proService.delete(orderId);
        return "redirect:/search";
    }

    @RequestMapping("/statistics")
    public String statistics(@RequestParam("time") String time, Model model) {
        List<VendSummary> statistics = proService.statistics(time);
        for (VendSummary statistic : statistics) {
            if (statistic.getVendorName().equals("小米")) {
                model.addAttribute("sales1", statistic.getSales());
            } else if (statistic.getVendorName().equals("华为")) {
                model.addAttribute("sales2", statistic.getSales());
            } else if (statistic.getVendorName().equals("苹果")) {
                model.addAttribute("sales3", statistic.getSales());
            }
            else if (statistic.getVendorName().equals("OPPO")) {
                model.addAttribute("sales4", statistic.getSales());
            }
        }
        model.addAttribute("time", time);
        return "form";
    }

    @GetMapping("/statisticso")
    public String statisticso() {
        return "form";
    }

    @RequestMapping("/statisticseve")
    public String statisticsEve(@RequestParam("vend_name") String vendName, Model model) {
        List<String> timeList = Arrays.asList("2017", "2018", "2019", "2020", "2021");
        List<VendSummary> statistics;
        for (String time : timeList) {
            String time1= time + "%";
            statistics = proService.querySales(time1, vendName);
            for (VendSummary statistic : statistics) {
                model.addAttribute("sales"+time, statistic.getSales());
            }
        }
        model.addAttribute("vendName", vendName);
        return "form";
    }

    @RequestMapping("/forecast")
    public String forecastData(@RequestParam("vend_name") String vendName, Model model) {
        List<String> timeList = Arrays.asList("2017", "2018", "2019", "2020", "2021");
        List<VendSummary> statistics;
        List<Integer> realDataList = new ArrayList<>();
        int sum = 0;
        double avg = 0;
        for (String time : timeList) {
            String time1= time + "%";
            statistics = proService.querySales(time1, vendName);
            for (VendSummary statistic : statistics) {
                int sales = statistic.getSales();
                sum += sales;
                realDataList.add(sales);
            }
        }
        avg = sum / 10.0;
        Integer forecast = proService.forecast(vendName, realDataList, sum, avg);
        model.addAttribute("forecast", forecast);
        model.addAttribute("vend_name", vendName);
        return "form";
    }
}
