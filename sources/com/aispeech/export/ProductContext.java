package com.aispeech.export;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ProductContext {
    public static final String OPTION_DELETE = "delete";
    public static final String OPTION_SET = "set";
    private String a;
    private List<Setting> b;

    /* synthetic */ ProductContext(Builder builder, byte b) {
        this(builder);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a;
        private List<Setting> b;

        public String getOption() {
            return this.a;
        }

        public Builder setOption(String str) {
            this.a = str;
            return this;
        }

        public List<Setting> getSettings() {
            return this.b;
        }

        public Builder setSettings(List<Setting> list) {
            this.b = list;
            return this;
        }

        public Builder setSetting(final Setting setting) {
            this.b = new ArrayList<Setting>() { // from class: com.aispeech.export.ProductContext.Builder.1
                {
                    add(Setting.this);
                }
            };
            return this;
        }

        public ProductContext build() {
            return new ProductContext(this, (byte) 0);
        }
    }

    public ProductContext(String str, List<Setting> list) {
        this.a = str;
        this.b = list;
    }

    private ProductContext(Builder builder) {
        this(builder.getOption(), builder.getSettings());
    }

    public String getOption() {
        return this.a;
    }

    public List<Setting> getSettings() {
        return this.b;
    }
}
