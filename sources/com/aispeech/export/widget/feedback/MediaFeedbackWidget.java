package com.aispeech.export.widget.feedback;
/* loaded from: classes.dex */
public class MediaFeedbackWidget extends FeedbackWidget {
    public MediaFeedbackWidget() {
        super(FeedbackWidget.TYPE_MEDIA);
    }

    public MediaFeedbackWidget addContentWidget(ContentFeedbackWidget contentFeedbackWidget) {
        return (MediaFeedbackWidget) super.addWidget(contentFeedbackWidget);
    }

    @Override // com.aispeech.export.widget.feedback.FeedbackWidget
    public MediaFeedbackWidget addExtra(String str, String str2) {
        return (MediaFeedbackWidget) super.addExtra(str, str2);
    }
}
