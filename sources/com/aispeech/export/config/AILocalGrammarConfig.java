package com.aispeech.export.config;
/* loaded from: classes.dex */
public class AILocalGrammarConfig {
    private String a;

    public String getRes() {
        return this.a;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private String a;

        public final Builder setRes(String str) {
            this.a = str;
            return this;
        }

        public final AILocalGrammarConfig build() {
            AILocalGrammarConfig aILocalGrammarConfig = new AILocalGrammarConfig();
            aILocalGrammarConfig.a = this.a;
            return aILocalGrammarConfig;
        }
    }
}
