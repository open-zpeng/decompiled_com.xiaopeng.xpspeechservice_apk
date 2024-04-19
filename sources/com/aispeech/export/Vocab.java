package com.aispeech.export;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class Vocab {
    public static final String ACTION_CLEAR_ALL = "ACTION_CLEAR_ALL";
    public static final String ACTION_CLEAR_AND_INSERT = "ACTION_CLEAR_AND_INSERT";
    public static final String ACTION_INSERT = "ACTION_INSERT";
    public static final String ACTION_REMOVE = "ACTION_REMOVE";
    private String a;
    private String b;
    private List<String> c;
    private boolean d;

    /* synthetic */ Vocab(Builder builder, byte b) {
        this(builder);
    }

    public String getAction() {
        return this.a;
    }

    public String getName() {
        return this.b;
    }

    public List<String> getContents() {
        return this.c;
    }

    public boolean isUseSegment() {
        return this.d;
    }

    public void updateContents(List<String> list) {
        this.c = list;
    }

    private Vocab(String str, String str2, List<String> list, boolean z) {
        this.d = true;
        this.a = str;
        this.b = str2;
        this.d = z;
        this.c = z ? a(list) : list;
    }

    private Vocab(Builder builder) {
        this(builder.getAction(), builder.getName(), builder.getContents(), builder.d);
    }

    private static List<String> a(List<String> list) {
        String[] split;
        if (list == null || list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                String replaceAll = str.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]", ",");
                if (!TextUtils.isEmpty(replaceAll)) {
                    for (String str2 : replaceAll.split(",")) {
                        if (!TextUtils.isEmpty(str2) && !arrayList.contains(str2)) {
                            arrayList.add(str2);
                        }
                    }
                    String replaceAll2 = replaceAll.replaceAll(",", "");
                    if (!arrayList.contains(replaceAll2)) {
                        arrayList.add(replaceAll2);
                    }
                }
            }
        }
        return arrayList;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a;
        private String b;
        private List<String> c;
        private boolean d = true;

        public String getAction() {
            return this.a;
        }

        public Builder setAction(String str) {
            this.a = str;
            return this;
        }

        public String getName() {
            return this.b;
        }

        public Builder setName(String str) {
            this.b = str;
            return this;
        }

        public List<String> getContents() {
            return this.c;
        }

        public Builder setContents(List<String> list) {
            this.c = list;
            return this;
        }

        public Builder addContent(String str) {
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(str);
            return this;
        }

        public Builder setUseSegment(boolean z) {
            this.d = z;
            return this;
        }

        public Vocab build() {
            if (this.b == null) {
                throw new IllegalArgumentException("Illegal Argument: null name");
            }
            if (this.a == null) {
                throw new IllegalArgumentException("Illegal Argument: null action");
            }
            List<String> list = this.c;
            if ((list == null || list.size() == 0) && Vocab.ACTION_INSERT.equals(getAction())) {
                throw new IllegalArgumentException("Illegal Argument: ACTION_INSERT without contents");
            }
            List<String> list2 = this.c;
            if ((list2 == null || list2.size() == 0) && Vocab.ACTION_REMOVE.equals(getAction())) {
                throw new IllegalArgumentException("Illegal Argument: ACTION_REMOVE without contents");
            }
            List<String> list3 = this.c;
            if ((list3 != null && list3.size() != 0) || !Vocab.ACTION_CLEAR_AND_INSERT.equals(getAction())) {
                return new Vocab(this, (byte) 0);
            }
            throw new IllegalArgumentException("Illegal Argument: ACTION_CLEAR_AND_INSERT without contents");
        }
    }

    public String toString() {
        return "Vocab{action='" + this.a + "', name='" + this.b + "', contents=" + this.c + "', useSegment=" + this.d + '}';
    }
}
