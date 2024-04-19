package com.aispeech.lite;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.aispeech.AIError;
import com.aispeech.common.Log;
import com.aispeech.lite.speech.EngineListener;
import com.aispeech.lite.speech.ProcessorListener;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class f implements ProcessorListener {
    private Handler a;
    private EngineListener b;

    protected abstract void a(a aVar, Object obj);

    public void a() {
        if (this.b != null) {
            this.b = null;
        }
        Handler handler = this.a;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.a = null;
        }
    }

    /* renamed from: com.aispeech.lite.f$4  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.MSG_INIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.MSG_CANCEL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.MSG_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.MSG_READY_FOR_SPEECH.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public final void a(EngineListener engineListener) {
        this.b = engineListener;
        this.a = new Handler(Looper.getMainLooper()) { // from class: com.aispeech.lite.f.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                a a2 = a.a(message.what);
                if (a2 == null) {
                    Log.e("BaseEngine", "undefined callback msg , check SDK code!");
                    return;
                }
                Log.d("BaseEngine", ">>>>>>[Callback]:" + a2.name());
                int i = AnonymousClass4.a[a2.ordinal()];
                if (i == 1) {
                    if (f.this.b != null) {
                        f.this.b.onInit(((Integer) message.obj).intValue());
                    }
                } else if (i == 2) {
                    f.this.d();
                } else if (i == 3) {
                    if (f.this.b != null) {
                        f.this.b.onError((AIError) message.obj);
                    }
                } else if (i == 4) {
                    if (f.this.b != null) {
                        f.this.b.onReadyForSpeech();
                    }
                } else {
                    f.this.a(a2, message.obj);
                }
            }
        };
    }

    public final void b(a aVar, Object obj) {
        if (this.a != null) {
            if (!c.a) {
                Message.obtain(this.a, aVar.o, obj).sendToTarget();
                return;
            }
            Handler handler = this.a;
            handler.handleMessage(Message.obtain(handler, aVar.o, obj));
        }
    }

    protected final void d() {
        Handler handler = this.a;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Map<String, Object> a(final String str, final Object obj, final String str2, final Object obj2) {
        return new HashMap<String, Object>() { // from class: com.aispeech.lite.f.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
                put(str, obj);
                put(str2, obj2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Map<String, Object> a(final String str, final Object obj, final String str2, final Object obj2, final String str3, final Object obj3) {
        return new HashMap<String, Object>() { // from class: com.aispeech.lite.f.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
                put(str, obj);
                put(str2, obj2);
                put(str3, obj3);
            }
        };
    }

    /* loaded from: classes.dex */
    public enum a {
        MSG_INIT(1),
        MSG_BEGINNING_OF_SPEECH(2),
        MSG_END_OF_SPEECH(3),
        MSG_BUFFER_RECEIVED(4),
        MSG_RECORED_RELEASED(5),
        MSG_ERROR(6),
        MSG_READY_FOR_SPEECH(7),
        MSG_RESULTS(8),
        MSG_RMS_CHANGED(9),
        MSG_RECORED_STOPPED(10),
        MSG_WAKEUP_STOPPED(11),
        MSG_GRAMMAR_SUCCESS(13),
        MSG_DOA_RESULT(14),
        MSG_CANCEL(15),
        MSG_RESULT_RECEIVE_DATA(16),
        MSG_PRE_WAKEUP(17),
        MSG_VPRINT_DATA(18),
        MSG_DM_ASR(19),
        MSG_DM_END(20),
        MSG_DM_QUERY(21),
        MSG_DM_CALL(22),
        MSG_DM_DISPLAY(23),
        MSG_DM_PLAY(24),
        MSG_NOT_ONE_SHOT(12),
        MSG_ONE_SHOT(25),
        MSG_UPDATE_RESULT(26),
        MSG_DM_RESULT(27);
        
        int o;

        a(int i) {
            this.o = i;
        }

        public static a a(int i) {
            a[] values;
            for (a aVar : values()) {
                if (i == aVar.o) {
                    return aVar;
                }
            }
            return null;
        }
    }

    @Override // com.aispeech.lite.speech.ProcessorListener
    public void onInit(int i) {
        b(a.MSG_INIT, Integer.valueOf(i));
    }

    @Override // com.aispeech.lite.speech.ProcessorListener
    public void onError(AIError aIError) {
        b(a.MSG_ERROR, aIError);
    }

    @Override // com.aispeech.lite.speech.ProcessorListener
    public void onReadyForSpeech() {
        b(a.MSG_READY_FOR_SPEECH, null);
    }

    @Override // com.aispeech.lite.speech.ProcessorListener
    public void onRawDataReceived(byte[] bArr, int i) {
        EngineListener engineListener = this.b;
        if (engineListener != null) {
            engineListener.onRawDataReceived(bArr, i);
        }
    }

    @Override // com.aispeech.lite.speech.ProcessorListener
    public void onRecorderStopped() {
    }

    @Override // com.aispeech.lite.speech.ProcessorListener
    public void onCancel() {
        b(a.MSG_CANCEL, null);
    }

    @Override // com.aispeech.lite.speech.ProcessorListener
    public void onResultDataReceived(byte[] bArr, int i, int i2) {
        EngineListener engineListener = this.b;
        if (engineListener != null) {
            engineListener.onResultDataReceived(bArr, i, i2);
        }
    }
}
