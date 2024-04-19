package com.aispeech.export.widget.feedback;
/* loaded from: classes.dex */
public class ContentFeedbackWidget extends FeedbackWidget {
    public ContentFeedbackWidget() {
        super("content");
    }

    public ContentFeedbackWidget setTitle(String str) {
        return (ContentFeedbackWidget) super.addContent("title", str);
    }

    public ContentFeedbackWidget setSubTitle(String str) {
        return (ContentFeedbackWidget) super.addContent("subTitle", str);
    }

    public ContentFeedbackWidget setLabel(String str) {
        return (ContentFeedbackWidget) super.addContent("label", str);
    }

    public ContentFeedbackWidget setImageUrl(String str) {
        return (ContentFeedbackWidget) super.addContent("imageUrl", str);
    }

    public ContentFeedbackWidget setLinkUrl(String str) {
        return (ContentFeedbackWidget) super.addContent("linkUrl", str);
    }

    @Override // com.aispeech.export.widget.feedback.FeedbackWidget
    public ContentFeedbackWidget addExtra(String str, String str2) {
        return (ContentFeedbackWidget) super.addExtra(str, str2);
    }
}
