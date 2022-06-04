package com.reportform.service.impl;

import com.reportform.entity.Order;
import com.reportform.mapper.SearchMapper;
import com.reportform.pojo.SecondSmoothingEntity;
import com.reportform.pojo.VendSummary;
import com.reportform.exception.LocalRunTimeException;
import com.reportform.mapper.ProMapper;
import com.reportform.service.ProService;
import com.reportform.util.DataForecast;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author xun
 * @create 2022/5/27 15:27
 */
@Service
public class ProServiceimpl implements ProService {

    final ProMapper proMapper;
    final SearchMapper searchMapper;

    public ProServiceimpl(ProMapper proMapper, SearchMapper searchMapper) {
        this.proMapper = proMapper;
        this.searchMapper = searchMapper;
    }


    /**
     * 统计手机品牌在某年份的销售数据
     * @param time 年份
     * @return 销售量
     */
    @Override
    public List<VendSummary> statistics(String time) {
        time = time + '%';
        return proMapper.findOrderByTimeAndName(time);
    }

    /**
     * 统计某品牌近五年的销售量
     * @param time 年份
     * @param vendName 品牌
     * @return
     */
    @Override
    public List<VendSummary> querySales(String time, String vendName) {
        time = time + '%';
        return proMapper.findSalesByVenName(time, vendName);
    }

    /**
     * 查询订单
     * 可根据地区、手机名、电商平台、订单号和时间进行查询
     * @param zone         地区
     * @param proName      手机名
     * @param platName     电商平台
     * @param orderId      订单号
     * @param time         创建订单时间
     * @param itemsPerPage 每页返回数据条数
     * @param pageNum      当前所在页数
     * @return 查询到的数据
     */
    @Override
    public List<Order> search(String zone, String proName, String platName, Integer orderId, String time,
                              Integer itemsPerPage, Integer pageNum) {
        if (time != null)
            time = time + '%';
        Integer proId = proMapper.findIdByProName(proName);
        Integer platId = proMapper.findIdByPlatName(platName);
        if (itemsPerPage == null) itemsPerPage = 10;
        if (pageNum == null) pageNum = 1;
        int offset = itemsPerPage * (pageNum - 1);
        List<Order> orders = searchMapper.searchByAll(proId, platId, zone, time, orderId, itemsPerPage, offset);
        if (orders.size() < 1) {
            throw new LocalRunTimeException("订单不存在o");
        }
        return orders;
    }

    /**
     * 计算数据的总页数
     * 方便分页查询
     * @param zone 地区
     * @param proName 手机型号
     * @param platName 电商平台
     * @param orderId 订单号
     * @param time 订单生成时间
     * @return 订单数据的总页数
     */
    @Override
    public Integer totalPageNum(String zone, String proName, String platName, Integer orderId, String time) {
        if (time != null)
            time = time + '%';
        Integer proId = proMapper.findIdByProName(proName);
        Integer platId = proMapper.findIdByPlatName(platName);
        return (searchMapper.searchAll(proId, platId, zone, time, orderId).size() - 1) / 10 + 1;
    }


    /**
     * 编辑订单
     * 支持更改地区和手机型号
     * @param orderId 订单号
     * @param zone    需要改成的地区
     * @param proName 需要改成的手机型号
     * @return 执行结果
     */
    @Override
    public String update(Integer orderId, String zone, String proName) {
        Integer proId = proMapper.findIdByProName(proName);
        proMapper.update(orderId, zone, proId);
        return "ok";
    }

    /**
     * 删除订单数据
     * @param orderId 订单号
     * @return 执行结果
     */
    @Override
    public String delete(Integer orderId) {
        List<Order> orders = proMapper.searchByOrderId(orderId);
        if (orders.size() < 1) {
            throw new LocalRunTimeException("订单不存在o");
        }
        proMapper.delete(orderId);
        return "ok";
    }

    /**
     * 增加订单信息
     *
     * @param proName  手机名称
     * @param platName 电商平台
     * @param zone     地区
     * @return 执行结果
     */
    @Override
    public String add(String proName, String platName, String zone) {
        Integer proId = proMapper.findIdByProName(proName);
        Integer platId = proMapper.findIdByPlatName(platName);
        if (proId == null) {
            throw new LocalRunTimeException("没有这个手机噢");
        }
        if (platId == null) {
            throw new LocalRunTimeException("找不到这个电商平台呢");
        }
        LocalDateTime time = LocalDateTime.now();
        proMapper.add(proId, platId, zone, time);
        return "ok";
    }

    /**
     * 预测下一年数据
     * 二次指数平滑法实现
     * @param vendName 手机品牌
     * @param realDataList 近五年的数据
     * @param sum 近五年数据总和
     * @param avg 五年数据总和的平均值
     * @return
     */
    @Override
    public Integer forecast(String vendName, List<Integer> realDataList, int sum, double avg) {
        SecondSmoothingEntity secondSmoothing = new SecondSmoothingEntity();
        secondSmoothing.setPredictTime(1);
        secondSmoothing.setLastSecondPredictParam(avg);
        secondSmoothing.setRealDataList(realDataList);
        secondSmoothing.setLastSinglePredictParam(avg);
        return DataForecast.secondExponentialSmoothingMethod(secondSmoothing);
    }
}
