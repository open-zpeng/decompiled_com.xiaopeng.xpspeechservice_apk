package com.aispeech.export.widget.feedback;
/* loaded from: classes.dex */
public class TextFeedbackWidget extends FeedbackWidget {
    public TextFeedbackWidget() {
        super("text");
    }

    public TextFeedbackWidget setText(String str) {
        return (TextFeedbackWidget) super.addContent("text", str);
    }

    @Override // com.aispeech.export.widget.feedback.FeedbackWidget
    public TextFeedbackWidget addExtra(String str, String str2) {
        return (TextFeedbackWidget) super.addExtra(str, str2);
    }
}
