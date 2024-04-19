package com.aispeech.export.intent;

import java.util.List;
/* loaded from: classes.dex */
public class AIAsrPlusIntent {
    private String a;
    private String b;
    private List<String> c;
    private boolean d = true;

    public String getServerName() {
        return this.a;
    }

    public void setServerName(String str) {
        this.a = str;
    }

    public String getOrganization() {
        return this.b;
    }

    public void setOrganization(String str) {
        this.b = str;
    }

    public List<String> getUsers() {
        return this.c;
    }

    public void setUsers(List<String> list) {
        this.c = list;
    }

    public boolean isCloudVprintVadEnable() {
        return this.d;
    }

    public void setCloudVprintVadEnable(boolean z) {
        this.d = z;
    }
}
