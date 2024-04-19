package com.aispeech.upload.http;

import java.net.MalformedURLException;
import java.net.URL;
/* loaded from: classes.dex */
public class HttpUrl {
    private final String scheme;
    private final String url;

    private HttpUrl(Builder builder) {
        this.scheme = builder.scheme;
        this.url = builder.url;
    }

    public String getUrl() {
        return this.url;
    }

    public URL url() {
        try {
            return new URL(this.url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpUrl resolve(String str) {
        Builder newBuilder = newBuilder(str);
        if (newBuilder != null) {
            return newBuilder.build();
        }
        return null;
    }

    public Builder newBuilder(String str) {
        Builder builder = new Builder();
        if (builder.parse(this, str) == Builder.ParseResult.SUCCESS) {
            return builder;
        }
        return null;
    }

    public boolean isHttps() {
        return this.scheme.equals("https");
    }

    public static HttpUrl parse(String str) {
        Builder builder = new Builder();
        if (builder.parse(null, str) == Builder.ParseResult.SUCCESS) {
            return builder.build();
        }
        return null;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        String scheme;
        String url;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public enum ParseResult {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME
        }

        public final HttpUrl build() {
            if (this.scheme == null) {
                throw new IllegalStateException("scheme == null");
            }
            return new HttpUrl(this);
        }

        public final Builder scheme(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            if (str.equalsIgnoreCase("http")) {
                this.scheme = "http";
            } else if (str.equalsIgnoreCase("https")) {
                this.scheme = "https";
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + str);
            }
            return this;
        }

        public static int defaultPort(String str) {
            if (str.equals("http")) {
                return 80;
            }
            if (str.equals("https")) {
                return 443;
            }
            return -1;
        }

        final ParseResult parse(HttpUrl httpUrl, String str) {
            this.url = str;
            if (str.startsWith("https:") || str.startsWith("http")) {
                if (str.regionMatches(true, 0, "https:", 0, 6)) {
                    this.scheme = "https";
                } else if (str.regionMatches(true, 0, "http:", 0, 5)) {
                    this.scheme = "http";
                } else {
                    return ParseResult.UNSUPPORTED_SCHEME;
                }
            } else if (httpUrl != null) {
                this.scheme = httpUrl.scheme;
            } else {
                return ParseResult.MISSING_SCHEME;
            }
            return ParseResult.SUCCESS;
        }
    }
}
