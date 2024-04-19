package com.aispeech.export.widget.callback;

import com.aispeech.export.widget.feedback.FeedbackWidget;
import io.reactivex.annotations.SchedulerSupport;
/* loaded from: classes.dex */
public enum CallbackWidgetType {
    TEXT(0, "text"),
    LIST(1, FeedbackWidget.TYPE_LIST),
    CONTENT(2, "content"),
    WEB(3, FeedbackWidget.TYPE_WEB),
    MEDIA(4, FeedbackWidget.TYPE_MEDIA),
    CUSTOM(5, SchedulerSupport.CUSTOM);
    
    private int a;
    private String b;

    CallbackWidgetType(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public final int getType() {
        return this.a;
    }

    public final String getName() {
        return this.b;
    }

    public static CallbackWidgetType getWidgetTypeByInt(int i) {
        CallbackWidgetType[] values;
        for (CallbackWidgetType callbackWidgetType : values()) {
            if (callbackWidgetType.getType() == i) {
                return callbackWidgetType;
            }
        }
        return null;
    }
}
