package com.aispeech.export;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class SkillContext {
    public static final String OPTION_DELETE = "delete";
    public static final String OPTION_SET = "set";
    private String a;
    private String b;
    private List<Setting> c;

    /* synthetic */ SkillContext(Builder builder, byte b) {
        this(builder);
    }

    public String getSkillId() {
        return this.a;
    }

    public String getOption() {
        return this.b;
    }

    public List<Setting> getSettings() {
        return this.c;
    }

    public SkillContext(String str, String str2, List<Setting> list) {
        this.a = str;
        this.b = str2;
        this.c = list;
    }

    private SkillContext(Builder builder) {
        this(builder.getSkillId(), builder.getOption(), builder.getSettings());
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a;
        private String b;
        private List<Setting> c;

        public String getSkillId() {
            return this.a;
        }

        public Builder setSkillId(String str) {
            this.a = str;
            return this;
        }

        public String getOption() {
            return this.b;
        }

        public Builder setOption(String str) {
            this.b = str;
            return this;
        }

        public List<Setting> getSettings() {
            return this.c;
        }

        public Builder setSettings(List<Setting> list) {
            this.c = list;
            return this;
        }

        public Builder setSetting(final Setting setting) {
            this.c = new ArrayList<Setting>() { // from class: com.aispeech.export.SkillContext.Builder.1
                {
                    add(Setting.this);
                }
            };
            return this;
        }

        public SkillContext build() {
            return new SkillContext(this, (byte) 0);
        }
    }
}
