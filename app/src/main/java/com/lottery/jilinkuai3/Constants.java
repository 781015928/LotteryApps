package com.lottery.jilinkuai3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by czg on 2017/12/28.
 */

public class Constants {
    private final static List<HomeApi> homeApis = new ArrayList();
    public static final String ZHONG_CAI_WANG_FEN_XI = "http://m.zhcw.com/fenxi";
    public static final String CHAT = "http://m.310win.com/chart/ChartIndex.aspx";
    public static final String ZI_XUN = "http://m.310win.com/chart/ChartIndex.aspx";

    public final static List<HomeApi> getHomeApis() {
        return homeApis;
    }

    static {

        homeApis.add(new HomeApi("4", "双色球"));
        homeApis.add(new HomeApi("6", "福彩3D"));
        homeApis.add(new HomeApi("2", "足球"));
        homeApis.add(new HomeApi("5", "大乐透"));
        homeApis.add(new HomeApi("1", "竞彩足球"));
        homeApis.add(new HomeApi("3", "竞彩篮球"));
        homeApis.add(new HomeApi("7", "数字排列"));
        homeApis.add(new HomeApi("8", "其他"));
    }


    public static class HomeApi {
        public String type;
        public String title;

        public HomeApi(String type, String title) {
            this.type = type;
            this.title = title;
        }
    }


}
