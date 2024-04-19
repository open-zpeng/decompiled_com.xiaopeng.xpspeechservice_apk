package com.aispeech.upload.http;

import com.aispeech.upload.http.Headers;
/* loaded from: classes.dex */
public class Request {
    private final Headers headers;
    private final String method;
    private final RequestBody requestBody;
    private final Object tag;
    private final int timeOut;
    private final HttpUrl url;

    private Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers.build();
        this.tag = builder.tag != null ? builder.tag : this;
        this.requestBody = builder.requestBody;
        this.timeOut = builder.timeOut;
    }

    public boolean isHttps() {
        return this.url.isHttps();
    }

    public HttpUrl url() {
        return this.url;
    }

    public String getMethod() {
        return this.method;
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private Headers.Builder headers;
        private String method;
        private RequestBody requestBody;
        private Object tag;
        private int timeOut;
        private HttpUrl url;

        public Builder() {
            this.timeOut = 30000;
            this.method = Method.GET;
            this.headers = new Headers.Builder();
        }

        private Builder(Request request) {
            this.timeOut = 30000;
            this.url = request.url;
            this.method = request.method;
            this.tag = request.tag;
            this.headers = request.headers.newBuilder();
        }

        public Builder url(HttpUrl httpUrl) {
            if (httpUrl == null) {
                throw new NullPointerException("url == null");
            }
            this.url = httpUrl;
            return this;
        }

        public Builder timeOut(int i) {
            this.timeOut = i;
            return this;
        }

        public Builder header(String str, String str2) {
            this.headers.set(str, str2);
            return this;
        }

        public Builder addHeader(String str, String str2) {
            this.headers.add(str, str2);
            return this;
        }

        public Builder removeHeader(String str) {
            this.headers.removeAll(str);
            return this;
        }

        public Builder url(String str) {
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
            HttpUrl parse = HttpUrl.parse(str);
            if (parse == null) {
                throw new IllegalArgumentException("unexpected url: " + str);
            }
            return url(parse);
        }

        public Builder tag(Object obj) {
            this.tag = obj;
            return this;
        }

        public Request build() {
            return new Request(this);
        }

        public Builder post(RequestBody requestBody) {
            this.method = Method.POST;
            this.requestBody = requestBody;
            return this;
        }
    }
}
