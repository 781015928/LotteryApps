package com.lottery.library.api.zx500.news;

import java.io.Serializable;

/**
 * @author czg
 * @date 2017/12/28
 */

public class NewsModel implements Serializable {
    public static final int DEFAULT_TYPE = 0;
    public static final int BIG_TYPE = 2;
    public static final int NO_IMAGE = 3;

    /**
     * canpublic : 0
     * hits : 9290
     * title : 姐的感觉双色球17153期:实单胆拖推荐
     * nickname : 姐的感觉
     * ispublic : 1
     * cover : http://img.500.com/upimages/openplatform/2017/0918/1505742612100.jpg
     * paytype : 1
     * publishtime : 2017-12-26 22:49
     * expiretime : 2017-12-28 21:15:00
     * typename : 双色球
     * headimg : http://img.m.500.com/esun/avatar/62/dd/62dd3ce276d211e7b83e.jpg?t=1501603163.2
     * shortcontent : 姐买彩票靠感觉，相信姐的感觉，就跟上姐的双色球实单拖推荐。(姐提醒各位彩友，小赌玩玩可以，大赌伤身，姐的方案3胆5拖蓝球单挑，每期投注20元，理性购彩，拒绝沉迷)上期推荐命中1胆1拖本期红球推荐：重号
     * threehitrate :
     * aid : 36445
     * type : 4
     * freecontent : 姐买彩票靠感觉，相信姐的感觉，就跟上姐的双色球实单拖推荐。(姐提醒各位彩友，小赌玩玩可以，大赌伤身，姐的方案3胆5拖蓝球单挑，每期投注20元，理性购彩，拒绝沉迷
     * uname : HUNt93xlUY%2BGolryVugUTA%3D%3D
     * comment : 0
     * url : http://zx.500.com/openplatform/n_4/2017/1226/060b200d2afe5c853a31a604ec8d6fbb.shtml
     */
    private String ls;

    public String getLs() {
        return ls;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    private String canpublic;
    private String hits;
    private String title;
    private String nickname;
    private String ispublic;
    private String cover;
    private String paytype;
    private String publishtime;
    private String expiretime;
    private String typename;
    private String headimg;
    private String shortcontent;
    private String threehitrate;
    private String aid;
    private String type;
    private String freecontent;
    private String uname;
    private String comment;
    private String url;

    public String getCanpublic() {
        return canpublic;
    }

    public void setCanpublic(String canpublic) {
        this.canpublic = canpublic;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getTitle() {
        return title;
    }

    private int viewType;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(String expiretime) {
        this.expiretime = expiretime;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getShortcontent() {
        return shortcontent;
    }

    public void setShortcontent(String shortcontent) {
        this.shortcontent = shortcontent;
    }

    public String getThreehitrate() {
        return threehitrate;
    }

    public void setThreehitrate(String threehitrate) {
        this.threehitrate = threehitrate;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFreecontent() {
        return freecontent;
    }

    public void setFreecontent(String freecontent) {
        this.freecontent = freecontent;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private Targets targets;

    public Targets getTargets() {
        return targets;
    }

    public void setTargets(Targets targets) {
        this.targets = targets;
    }

    public static class Targets implements Serializable{
        private String maxrednum;
        private String tenprojecthits;

        public String getMaxrednum() {
            return maxrednum;
        }

        public void setMaxrednum(String maxrednum) {
            this.maxrednum = maxrednum;
        }

        public String getTenprojecthits() {
            return tenprojecthits;
        }

        public void setTenprojecthits(String tenprojecthits) {
            this.tenprojecthits = tenprojecthits;
        }
    }
}
