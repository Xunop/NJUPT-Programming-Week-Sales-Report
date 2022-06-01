package com.reportform.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author xun
 * @create 2022/5/28 16:16
 */
public class DataUtil {
    static final Pattern NAME_PATTERN = Pattern.compile("(.*?)（.*）");
    static final Pattern PRICE_PATTERN = Pattern.compile("￥(\\d+)");

    public List<Object[]> getInfo() throws IOException {
        List<Object[]> infoList = new ArrayList<>();
        File file = new File("D:\\下载\\【2022小米手机排行榜】小米手机哪款好_小米手机推荐-ZOL排行榜.html");
        Document document = Jsoup.parse(file, "GBK", "");
        document.getElementsByClass("rank-list__item").forEach(e -> {
            Matcher nameMatcher = NAME_PATTERN.matcher(e.getElementsByClass("rank__name").text());
            String rankName = nameMatcher.find() ? nameMatcher.group(1) : "";
            if (rankName.isBlank()) return;
            Matcher priceMatcher = PRICE_PATTERN.matcher(e.getElementsByClass("rank__price").text());
            int rankPrice = priceMatcher.find() ? Integer.parseInt(priceMatcher.group(1)) : 0;
            infoList.add(new Object[]{rankName, rankPrice});
        });
        return infoList;
    }
}
