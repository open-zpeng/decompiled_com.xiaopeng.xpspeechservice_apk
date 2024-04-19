package com.aispeech.export.config;
/* loaded from: classes.dex */
public class AICloudVprintConfig {
    private Mode a = Mode.TEXT_HALF_RELATED;
    private RES b = RES.TEXT_DP_SR;
    private String c = "https://vpr.duiopen.com";
    private String d;
    private String e;

    /* loaded from: classes.dex */
    public enum Mode {
        TEXT_NO_RELATED(1),
        TEXT_HALF_RELATED(2),
        TEXT_RELATED(3),
        TEXT_NO_RELATED_SHORT_TIME(4);
        
        private final int a;

        Mode(int i) {
            this.a = i;
        }

        public final int getValue() {
            return this.a;
        }
    }

    /* loaded from: classes.dex */
    public enum RES {
        TEXT_TI_SR("ti-sr"),
        TEXT_DP_SR("dp-sr"),
        TEXT_STI_SR("sti-sr"),
        TEXT_LTI_SR("lti-sr");
        
        private final String a;

        RES(String str) {
            this.a = str;
        }

        public final String getValue() {
            return this.a;
        }
    }

    public void setRes(RES res) {
        this.b = res;
    }

    public RES getRes() {
        return this.b;
    }

    public Mode getOpcode() {
        return this.a;
    }

    public void setOpcode(Mode mode) {
        this.a = mode;
    }

    public String getHost() {
        return this.c;
    }

    public void setHost(String str) {
        this.c = str;
    }

    public String getApiKey() {
        return this.d;
    }

    public void setApiKey(String str) {
        this.d = str;
    }

    public String getProductID() {
        return this.e;
    }

    public void setProductID(String str) {
        this.e = str;
    }

    public String toString() {
        return "AICloudVprintConfig{opcode=" + this.a + ", host='" + this.c + "', apiKey='" + this.d + "', productID='" + this.e + "', res =" + this.b + '}';
    }
}
