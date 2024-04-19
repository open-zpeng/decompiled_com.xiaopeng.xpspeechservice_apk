package com.aispeech.export.listeners;

import com.aispeech.export.Command;
import com.aispeech.export.DmInfo;
import com.aispeech.export.NativeApi;
import com.aispeech.export.Speaker;
import com.aispeech.export.widget.callback.CallbackWidget;
import com.aispeech.export.widget.callback.CallbackWidgetType;
import com.aispeech.lite.speech.EngineListener;
/* loaded from: classes.dex */
public interface AICloudDMListener extends EngineListener {
    void onAsr(boolean z, String str);

    void onBeginningOfSpeech();

    void onCall(Command command);

    void onDisplay(CallbackWidgetType callbackWidgetType, CallbackWidget callbackWidget);

    void onDmResult(DmInfo dmInfo);

    void onEnd(String str);

    void onEndOfSpeech();

    void onPlay(Speaker speaker);

    void onQuery(NativeApi nativeApi);

    void onRmsChanged(float f);
}
