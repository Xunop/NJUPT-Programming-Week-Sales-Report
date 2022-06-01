package com.reportform;

import com.reportform.mapper.ProMapper;
import com.reportform.util.DataUtil;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@SpringBootTest
class ReportFormApplicationTests {
    @Autowired
    ProMapper proMapper;

    // 爬取数据
    @Test
    void contextLoads() throws IOException {
        List<Object[]> infoList = new DataUtil().getInfo();
        for (Object[] objects : infoList) {
            proMapper.insertData((String) objects[0], (Integer) objects[1]);
        }
    }

    @Test
    void test() {
        System.out.println("\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n");
    }
}
