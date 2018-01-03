package com.lottery.library.api.caipiao163.lotterhall;

import com.lottery.library.http.ApiResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by czg on 2017/12/29.
 */

public class LotterHallResponse extends ApiResponse<List<LotterHallModel>> {

    public LotterHallResponse(Response response) {
        super(response);
    }

    @Override
    public List<LotterHallModel> getBody() throws IOException {
        ArrayList data = new ArrayList();
        String url = getApiRequest().getRequest().url().toString();
        Document doc = Jsoup.parse(new URL(url).openStream(), "utf-8", "http://caipiao.163.com");
        Elements awardList = doc.getElementsByClass("awardList");
        if (awardList.size() > 0) {
            awardList = awardList.get(0).children();
            if (awardList.size() > 0) {
                awardList = awardList.get(0).children();
            }
        }

        for (Element element : awardList) {
            LotterHallModel lotterHallModel = new LotterHallModel();
            List numBalls = new ArrayList();

            Elements em = element.select("em");
            if (em.size() > 0) {
                if (em.size() == 2) {
                    lotterHallModel.setTittle(element.select("h2").text());
                    lotterHallModel.setBtn(em.get(1).text());
                } else {
                    lotterHallModel.setTittle(element.select("h2").text());
                    for (int i = 1; i < em.size(); i++) {
                        Element elementBall = em.get(i);
                        LotterHallModel.NumBall numBall = new LotterHallModel.NumBall();
                        numBall.setColor(elementBall.attr("class"));
                        numBall.setNum(elementBall.attr("rel"));
                        numBalls.add(numBall);
                    }
                }
            }

            lotterHallModel.setNumBalls(numBalls);
            String href = element.select("a").attr("href");
            lotterHallModel.setHref(href);

            Elements date = element.getElementsByClass("date");
            if (date.size() > 0) {
                lotterHallModel.setDate(date.get(0).text());
            }


            lotterHallModel.setContent(element.html());

            data.add(lotterHallModel);

        }
        return data;

    }
}
