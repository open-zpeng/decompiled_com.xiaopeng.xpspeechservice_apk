package com.aispeech.export.widget.feedback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public class FeedbackWidget {
    public static final String TYPE_CONTENT = "content";
    public static final String TYPE_LIST = "list";
    public static final String TYPE_MEDIA = "media";
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_WEB = "web";
    public static final String WIDGET_EXTRA = "extra";
    public static final String WIDGET_IMAGEURL = "imageUrl";
    public static final String WIDGET_LABEL = "label";
    public static final String WIDGET_LINKURL = "linkUrl";
    public static final String WIDGET_SUBTITLE = "subTitle";
    public static final String WIDGET_TEXT = "text";
    public static final String WIDGET_TITLE = "title";
    public static final String WIDGET_URL = "url";
    private Map<String, Object> a = new a((byte) 0);
    private Map<String, Object> b = new a((byte) 0);
    private ArrayList<FeedbackWidget> c = new WidgetArray();

    /* loaded from: classes.dex */
    static class a<S, O> extends HashMap<String, Object> {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // java.util.AbstractMap
        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            Iterator<String> it = keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                Object obj = get(next);
                sb.append("\"");
                sb.append(next);
                sb.append("\":");
                if (obj instanceof String) {
                    sb.append("\"");
                    sb.append(obj);
                    sb.append("\"");
                } else {
                    sb.append(obj);
                }
                if (!it.hasNext()) {
                    break;
                }
                sb.append(",");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class WidgetArray<O> extends ArrayList<FeedbackWidget> {
        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < size(); i++) {
                FeedbackWidget feedbackWidget = get(i);
                sb.append("{");
                sb.append(feedbackWidget);
                sb.append("}");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public FeedbackWidget(String str) {
        this.a.put("type", str);
        this.a.put("duiWidget", str);
    }

    public FeedbackWidget setType(String str) {
        this.a.put("type", str);
        this.a.put("duiWidget", str);
        return this;
    }

    public FeedbackWidget addContent(String str, String str2) {
        this.a.put(str, str2);
        return this;
    }

    public FeedbackWidget addWidget(FeedbackWidget feedbackWidget) {
        this.c.add(feedbackWidget);
        return this;
    }

    public FeedbackWidget addExtra(String str, String str2) {
        this.b.put(str, str2);
        return this;
    }

    public FeedbackWidget addExtra(String str, Object obj) {
        this.b.put(str, obj);
        return this;
    }

    public String toString() {
        if (this.b.size() > 0) {
            this.a.put(WIDGET_EXTRA, this.b);
        }
        String str = (String) this.a.get("type");
        if (TYPE_LIST.equals(str) || TYPE_MEDIA.equals(str)) {
            ArrayList arrayList = new ArrayList(this.c);
            this.a.put("count", Integer.valueOf(arrayList.size()));
            this.a.put("content", arrayList);
        }
        return this.a.toString();
    }
}
