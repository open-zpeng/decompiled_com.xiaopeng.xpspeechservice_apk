package com.aispeech.export.widget.callback;

import android.text.TextUtils;
import com.aispeech.common.Log;
import com.aispeech.export.widget.feedback.FeedbackWidget;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class CallbackWidget {
    public JSONObject extra;
    public String intentName;
    public JSONArray recommendations;
    public String skillId;
    public String taskName;
    public int type;

    /* JADX INFO: Access modifiers changed from: protected */
    public CallbackWidget(JSONObject jSONObject, int i, String str, String str2, String str3) {
        this.type = i;
        this.intentName = str3;
        this.skillId = str;
        this.taskName = str2;
        this.recommendations = jSONObject.optJSONArray("recommendations");
        this.extra = jSONObject.optJSONObject(FeedbackWidget.WIDGET_EXTRA);
    }

    public String getIntentName() {
        return this.intentName;
    }

    public String getSkillId() {
        return this.skillId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public JSONArray getRecommendations() {
        return this.recommendations;
    }

    public JSONObject getExtra() {
        return this.extra;
    }

    public int getType() {
        return this.type;
    }

    public static CallbackWidget transForm(JSONObject jSONObject, String str, String str2, String str3) {
        if (jSONObject == null || !jSONObject.has("type")) {
            return null;
        }
        String optString = jSONObject.optString("type");
        Log.i("CallbackWidget", "create widget type is :" + optString);
        if (TextUtils.equals(CallbackWidgetType.TEXT.getName(), optString)) {
            return new TextCallbackWidget(jSONObject, CallbackWidgetType.TEXT.getType(), str, str2, str3);
        }
        if (TextUtils.equals(CallbackWidgetType.MEDIA.getName(), optString)) {
            return new MediaCallbackWidget(jSONObject, CallbackWidgetType.MEDIA.getType(), str, str2, str3);
        }
        if (TextUtils.equals(CallbackWidgetType.CONTENT.getName(), optString)) {
            return new ContentCallbackWidget(jSONObject, CallbackWidgetType.CONTENT.getType(), str, str2, str3);
        }
        if (TextUtils.equals(CallbackWidgetType.LIST.getName(), optString)) {
            return new ListCallbackWidget(jSONObject, CallbackWidgetType.LIST.getType(), str, str2, str3);
        }
        if (TextUtils.equals(CallbackWidgetType.WEB.getName(), optString)) {
            return new WebCallbackWidget(jSONObject, CallbackWidgetType.WEB.getType(), str, str2, str3);
        }
        if (TextUtils.equals(CallbackWidgetType.CUSTOM.getName(), optString)) {
            return new CustomCallbackWidget(jSONObject, CallbackWidgetType.CUSTOM.getType(), str, str2, str3);
        }
        Log.e("CallbackWidget", "unknown widget type: " + optString);
        return null;
    }
}
