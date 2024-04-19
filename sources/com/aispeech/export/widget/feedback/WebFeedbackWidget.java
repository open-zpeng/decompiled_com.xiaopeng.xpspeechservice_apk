package com.aispeech.export.widget.feedback;

import java.util.ArrayList;
/* loaded from: classes.dex */
public class WebFeedbackWidget extends FeedbackWidget {
    public WebFeedbackWidget() {
        super(FeedbackWidget.TYPE_WEB);
        new ArrayList();
    }

    public WebFeedbackWidget setUrl(String str) {
        return (WebFeedbackWidget) super.addContent("url", str);
    }

    @Override // com.aispeech.export.widget.feedback.FeedbackWidget
    public WebFeedbackWidget addExtra(String str, String str2) {
        return (WebFeedbackWidget) super.addExtra(str, str2);
    }
}
