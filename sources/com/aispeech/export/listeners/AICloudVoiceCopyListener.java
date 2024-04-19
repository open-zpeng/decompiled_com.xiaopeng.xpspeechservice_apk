package com.aispeech.export.listeners;

import java.io.IOException;
/* loaded from: classes.dex */
public interface AICloudVoiceCopyListener {
    void onCustomize(int i, String str, IOException iOException);

    void onDelete(int i, String str, IOException iOException);

    void onInit(int i, String str);

    void onQuery(int i, String str, IOException iOException);

    void onRecordText(int i, String str, IOException iOException);

    void onTraining(int i, String str, IOException iOException);

    void onUpload(int i, String str, IOException iOException);
}
