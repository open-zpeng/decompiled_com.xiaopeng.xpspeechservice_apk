package com.aispeech.export.widget.feedback;

import java.util.ArrayList;
/* loaded from: classes.dex */
public class ListFeedbackWidget extends FeedbackWidget {
    public ListFeedbackWidget() {
        super(FeedbackWidget.TYPE_LIST);
        new ArrayList();
    }

    public ListFeedbackWidget addContentWidget(ContentFeedbackWidget contentFeedbackWidget) {
        return (ListFeedbackWidget) super.addWidget(contentFeedbackWidget);
    }

    @Override // com.aispeech.export.widget.feedback.FeedbackWidget
    public ListFeedbackWidget addExtra(String str, String str2) {
        return (ListFeedbackWidget) super.addExtra(str, str2);
    }
}
