package com.aispeech.common;

import com.aispeech.AIError;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class DDSJSONResultParser {
    private StringBuilder a = new StringBuilder();

    public DDSResultParseBean parse(String str) {
        DDSResultParseBean dDSResultParseBean = new DDSResultParseBean();
        dDSResultParseBean.a = JSONUtil.build(str);
        if (dDSResultParseBean.a != null) {
            Object optQuietly = JSONUtil.optQuietly(dDSResultParseBean.a, "eof");
            if (optQuietly != null) {
                dDSResultParseBean.setEof(((Integer) optQuietly).intValue());
            }
            Object optQuietly2 = JSONUtil.optQuietly(dDSResultParseBean.a, "text");
            if (optQuietly2 != null) {
                this.a.append(optQuietly2);
            }
            Object optQuietly3 = JSONUtil.optQuietly(dDSResultParseBean.a, "var");
            StringBuilder sb = new StringBuilder();
            sb.append(this.a.toString());
            sb.append(optQuietly3 == null ? "" : optQuietly3);
            dDSResultParseBean.setText(sb.toString());
            JSONObject jSONObject = dDSResultParseBean.a;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.a.toString());
            if (optQuietly3 == null) {
                optQuietly3 = "";
            }
            sb2.append(optQuietly3);
            JSONUtil.putQuietly(jSONObject, "text", sb2.toString());
            Object optQuietly4 = JSONUtil.optQuietly(dDSResultParseBean.a, "sessionId");
            if (optQuietly4 != null) {
                dDSResultParseBean.setSessionId((String) optQuietly4);
            }
            Object optQuietly5 = JSONUtil.optQuietly(dDSResultParseBean.a, AIError.KEY_RECORD_ID);
            if (optQuietly5 != null) {
                dDSResultParseBean.setRecordId((String) optQuietly5);
            }
            Object optQuietly6 = JSONUtil.optQuietly(dDSResultParseBean.a, "skillId");
            if (optQuietly6 != null) {
                dDSResultParseBean.setSkillId((String) optQuietly6);
            }
            Object optQuietly7 = JSONUtil.optQuietly(dDSResultParseBean.a, "contextId");
            if (optQuietly7 != null) {
                dDSResultParseBean.setContextId((String) optQuietly7);
            }
            Object optQuietly8 = JSONUtil.optQuietly(dDSResultParseBean.a, "nlu");
            if (optQuietly8 != null) {
                dDSResultParseBean.setNlu((JSONObject) optQuietly8);
            }
            Object optQuietly9 = JSONUtil.optQuietly(dDSResultParseBean.a, "dm");
            if (optQuietly9 != null) {
                dDSResultParseBean.setDm((JSONObject) optQuietly9);
            }
            Object optQuietly10 = JSONUtil.optQuietly(dDSResultParseBean.a, AIError.KEY_TEXT);
            if (optQuietly10 != null) {
                dDSResultParseBean.setError((JSONObject) optQuietly10);
            }
        }
        return dDSResultParseBean;
    }

    public void destroy() {
        this.a = null;
    }

    /* loaded from: classes.dex */
    public static class DDSResultParseBean {
        private JSONObject a;
        private String b;
        private String c;
        private String d;
        private JSONObject e;
        private JSONObject f;
        private int g;
        private String h;
        private String i;
        private String j;
        private String k;
        private JSONObject l;

        public JSONObject getError() {
            return this.l;
        }

        public void setError(JSONObject jSONObject) {
            this.l = jSONObject;
        }

        public String getContextId() {
            return this.k;
        }

        public void setContextId(String str) {
            this.k = str;
        }

        public String getSkillId() {
            return this.j;
        }

        public void setSkillId(String str) {
            this.j = str;
        }

        public String getText() {
            return this.c;
        }

        public void setText(String str) {
            this.c = str;
        }

        public JSONObject getJso() {
            return this.a;
        }

        public void setJso(JSONObject jSONObject) {
            this.a = jSONObject;
        }

        public String getVar() {
            return this.b;
        }

        public void setVar(String str) {
            this.b = str;
        }

        public String getContext() {
            return this.d;
        }

        public void setContext(String str) {
            this.d = str;
        }

        public JSONObject getDm() {
            return this.e;
        }

        public void setDm(JSONObject jSONObject) {
            this.e = jSONObject;
        }

        public JSONObject getNlu() {
            return this.f;
        }

        public void setNlu(JSONObject jSONObject) {
            this.f = jSONObject;
        }

        public int getEof() {
            return this.g;
        }

        public void setEof(int i) {
            this.g = i;
        }

        public String getRecordId() {
            return this.h;
        }

        public void setRecordId(String str) {
            this.h = str;
        }

        public String getSessionId() {
            return this.i;
        }

        public void setSessionId(String str) {
            this.i = str;
        }

        public String toString() {
            return "DDSJSONResultParser{jso=" + this.a + ", var='" + this.b + "', text='" + this.c + "', context='" + this.d + "', dm=" + this.e + ", nlu=" + this.f + ", eof=" + this.g + ", recordId='" + this.h + "', sessionId='" + this.i + "', skillId='" + this.j + "', contextId='" + this.k + "', error=" + this.l + '}';
        }
    }
}
