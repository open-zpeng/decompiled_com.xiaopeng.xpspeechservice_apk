package com.aispeech.upload;
/* loaded from: classes.dex */
class ErrorJson {
    static String errorJson = "{\n    \"message\": {\n        \"logId\": LOGID,\n        \"module\": \"cloud_exception\",\n        \"num\": NUM\n    },\n    \"time\": TIME,\n    \"project\": \"dui\",\n    \"level\": \"info\",\n    \"tag\": \"upload_error_num\"\n}";

    ErrorJson() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getErrorJson(int i, int i2) {
        return errorJson.replace("TIME", String.valueOf(System.currentTimeMillis() / 1000)).replace("LOGID", String.valueOf(i)).replace("NUM", String.valueOf(i2));
    }
}
