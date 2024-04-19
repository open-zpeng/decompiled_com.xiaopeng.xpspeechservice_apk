package com.aispeech.export.listeners;
/* loaded from: classes.dex */
public interface AICloudVprintListener {

    /* loaded from: classes.dex */
    public interface AudioToolListener {
        void onError(int i, String str);

        void onRecordStart();

        void onRecordStop();
    }

    void onInit(int i, String str);

    void onPrepareRegister(int i, String str, Exception exc);

    void onPrepareVerify(int i, String str, Exception exc);

    void onRegister(int i, String str, Exception exc);

    void onUnregister(int i, String str, Exception exc);

    void onVerify(int i, String str, Exception exc);
}
