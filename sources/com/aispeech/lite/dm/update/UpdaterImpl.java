package com.aispeech.lite.dm.update;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.aispeech.auth.d;
import com.aispeech.export.ProductContext;
import com.aispeech.export.SkillContext;
import com.aispeech.export.Vocab;
import com.aispeech.export.listeners.AIUpdateListener;
import com.aispeech.lite.c;
/* loaded from: classes.dex */
public class UpdaterImpl implements IUpdater {
    private Context a;
    private CInfoV1Impl b;
    private CInfoV2Impl c;
    private d d;
    private Handler e;
    private AIUpdateListener f;

    /* synthetic */ UpdaterImpl(Builder builder, byte b) {
        this(builder);
    }

    static /* synthetic */ void a(UpdaterImpl updaterImpl) {
        Handler handler = updaterImpl.e;
        if (handler == null) {
            return;
        }
        Message.obtain(handler, 0, null).sendToTarget();
    }

    static /* synthetic */ void b(UpdaterImpl updaterImpl) {
        Handler handler = updaterImpl.e;
        if (handler == null) {
            return;
        }
        Message.obtain(handler, -1, null).sendToTarget();
    }

    private UpdaterImpl(String str, String str2, String str3, boolean z) {
        this.d = c.a();
        this.a = c.b();
        this.e = new Handler(this.a.getMainLooper()) { // from class: com.aispeech.lite.dm.update.UpdaterImpl.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i != -1) {
                    if (i == 0 && UpdaterImpl.this.f != null) {
                        UpdaterImpl.this.f.success();
                    }
                } else if (UpdaterImpl.this.f != null) {
                    UpdaterImpl.this.f.failed();
                }
            }
        };
        this.b = new CInfoV1Impl(str, this.d.f(), this.d.g(), this.d.a(), str3, new CInfoListener() { // from class: com.aispeech.lite.dm.update.UpdaterImpl.1
            @Override // com.aispeech.lite.dm.update.CInfoListener
            public void onUploadSuccess() {
                UpdaterImpl.a(UpdaterImpl.this);
            }

            @Override // com.aispeech.lite.dm.update.CInfoListener
            public void onUploadFailed() {
                UpdaterImpl.b(UpdaterImpl.this);
            }
        });
        this.c = new CInfoV2Impl(str2, this.d.f(), this.d.g(), this.d.a(), str3, z, new CInfoListener() { // from class: com.aispeech.lite.dm.update.UpdaterImpl.2
            @Override // com.aispeech.lite.dm.update.CInfoListener
            public void onUploadSuccess() {
                UpdaterImpl.a(UpdaterImpl.this);
            }

            @Override // com.aispeech.lite.dm.update.CInfoListener
            public void onUploadFailed() {
                UpdaterImpl.b(UpdaterImpl.this);
            }
        });
    }

    private UpdaterImpl(Builder builder) {
        this(builder.getV1Host(), builder.getWebSocketHost(), builder.getAliasKey(), builder.isFullDuplex());
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String a;
        private String b;
        private String c;
        private boolean d;

        public String getV1Host() {
            return this.a;
        }

        public Builder setV1Host(String str) {
            this.a = str;
            return this;
        }

        public String getWebSocketHost() {
            return this.b;
        }

        public Builder setWebSocketHost(String str) {
            this.b = str;
            return this;
        }

        public String getAliasKey() {
            return this.c;
        }

        public Builder setAliasKey(String str) {
            this.c = str;
            return this;
        }

        public boolean isFullDuplex() {
            return this.d;
        }

        public Builder setFullDuplex(boolean z) {
            this.d = z;
            return this;
        }

        public UpdaterImpl build() {
            return new UpdaterImpl(this, (byte) 0);
        }
    }

    @Override // com.aispeech.lite.dm.update.IUpdater
    public void updateVocabs(AIUpdateListener aIUpdateListener, Vocab... vocabArr) {
        this.f = aIUpdateListener;
        CInfoV1Impl cInfoV1Impl = this.b;
        if (cInfoV1Impl != null) {
            cInfoV1Impl.uploadVocabs(vocabArr);
        }
    }

    @Override // com.aispeech.lite.dm.update.IUpdater
    public void updateProductContext(AIUpdateListener aIUpdateListener, ProductContext productContext) {
        this.f = aIUpdateListener;
        CInfoV2Impl cInfoV2Impl = this.c;
        if (cInfoV2Impl != null) {
            cInfoV2Impl.uploadProductContext(productContext);
        }
    }

    @Override // com.aispeech.lite.dm.update.IUpdater
    public void updateSkillContext(AIUpdateListener aIUpdateListener, SkillContext skillContext) {
        this.f = aIUpdateListener;
        CInfoV2Impl cInfoV2Impl = this.c;
        if (cInfoV2Impl != null) {
            cInfoV2Impl.uploadSkillContext(skillContext);
        }
    }
}
