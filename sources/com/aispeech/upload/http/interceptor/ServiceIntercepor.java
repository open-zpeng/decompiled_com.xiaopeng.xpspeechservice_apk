package com.aispeech.upload.http.interceptor;

import android.text.TextUtils;
import com.aispeech.common.Util;
import com.aispeech.upload.http.Headers;
import com.aispeech.upload.http.Method;
import com.aispeech.upload.http.Request;
import com.aispeech.upload.http.RequestBody;
import com.aispeech.upload.http.Response;
import com.aispeech.upload.http.interceptor.Interceptor;
import com.aispeech.upload.util.LogUtil;
import com.aispeech.upload.util.Tag;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
/* loaded from: classes.dex */
public class ServiceIntercepor implements Interceptor {
    private static final String TAG = Tag.getTag("HTTP");

    @Override // com.aispeech.upload.http.interceptor.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        Request request = chain.request();
        HttpURLConnection httpURLConnection = (HttpURLConnection) request.url().url().openConnection();
        httpURLConnection.setRequestMethod(request.getMethod());
        httpURLConnection.setDoOutput(Method.isOut(request.getMethod()));
        httpURLConnection.setDoInput(true);
        httpURLConnection.setConnectTimeout(request.getTimeOut());
        httpURLConnection.setReadTimeout(request.getTimeOut());
        httpURLConnection.setRequestProperty("connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("Charsert", Util.UTF8);
        httpURLConnection.setChunkedStreamingMode(10240);
        Headers headers = request.getHeaders();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            httpURLConnection.setRequestProperty(headers.name(i), headers.value(i));
        }
        RequestBody requestBody = request.getRequestBody();
        if (requestBody != null) {
            requestBody.addRequestProperty(httpURLConnection);
        }
        httpURLConnection.connect();
        if (requestBody != null && Method.isOut(request.getMethod())) {
            requestBody.onWriteBody(httpURLConnection.getOutputStream());
        }
        int responseCode = httpURLConnection.getResponseCode();
        String str = TAG;
        LogUtil.d(str, "responseCode = " + responseCode);
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (hasBody(responseCode)) {
            return new Response(responseCode, getRealInputStream(responseCode, headerFields, httpURLConnection), headerFields);
        }
        return null;
    }

    private InputStream getRealInputStream(int i, Map<String, List<String>> map, HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream;
        if (i >= 400) {
            inputStream = httpURLConnection.getErrorStream();
        } else {
            inputStream = httpURLConnection.getInputStream();
        }
        String contentEncoding = httpURLConnection.getContentEncoding();
        if (!TextUtils.isEmpty(contentEncoding) && contentEncoding.contains("gzip")) {
            return new GZIPInputStream(inputStream);
        }
        return inputStream;
    }

    private boolean hasBody(int i) {
        return i < 100 || i >= 200 || i != 204 || i != 205 || i < 300 || i >= 400;
    }
}
