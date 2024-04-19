package com.aispeech.export.widget.callback;

import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class MediaCallbackWidget extends CallbackWidget {
    public static final String CONTENT = "content";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String ITEMS_PERPAGE = "itemsPerPage";
    public static final String TOTAL_PAGES = "totalPages";
    private int a;
    private int b;
    private int c;
    private JSONArray d;

    public MediaCallbackWidget(JSONObject jSONObject, int i, String str, String str2, String str3) {
        super(jSONObject, i, str, str2, str3);
        setCurrentPage(jSONObject.optInt("currentPage"));
        setContent(jSONObject.optJSONArray("content"));
        setItemsPerPage(jSONObject.optInt("itemsPerPage"));
        setTotalPages(jSONObject.optInt("totalPages"));
    }

    public int getTotalPages() {
        return this.a;
    }

    public void setTotalPages(int i) {
        this.a = i;
    }

    public int getItemsPerPage() {
        return this.b;
    }

    public void setItemsPerPage(int i) {
        this.b = i;
    }

    public int getCurrentPage() {
        return this.c;
    }

    public void setCurrentPage(int i) {
        this.c = i;
    }

    public JSONArray getContent() {
        return this.d;
    }

    public void setContent(JSONArray jSONArray) {
        this.d = jSONArray;
    }
}
