package com.lottery.jilinkuai3.activity;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by 78101 on 2018/1/4.
 */

public class ZhcwActivity extends WebContentActivity {

    @Override
    protected Element parseWebContent(Document document) {
        Element header = document.selectFirst("header");
        if (header != null) {
            header.remove();
        }
        Element footer = document.getElementById("footer");
        if (footer != null) {
            footer.remove();
        }

        return document;
    }
}
