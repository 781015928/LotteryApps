package com.lottery.library.api.caipiao163.lotterhall;

import java.util.List;

/**
 * Created by czg on 2017/12/29.
 */

public class LotterHallModel {
    private String content;
    private String href;
    private String date;
    private List<NumBall> numBalls;
    private String btn;
    private String tittle;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }


    public List<NumBall> getNumBalls() {
        return numBalls;
    }

    public void setNumBalls(List<NumBall> numBalls) {
        this.numBalls = numBalls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public static class NumBall {
        private String color;
        private String num;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
