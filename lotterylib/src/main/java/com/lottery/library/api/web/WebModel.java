package com.lottery.library.api.web;

import java.io.Serializable;

/**
 * @author czg
 * @date 2017/12/28
 */

public class WebModel implements Serializable {
    String content;
    String title;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
