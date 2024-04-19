package com.aispeech.lite.dm.update;

import com.aispeech.export.ProductContext;
import com.aispeech.export.SkillContext;
import com.aispeech.export.Vocab;
import com.aispeech.export.listeners.AIUpdateListener;
/* loaded from: classes.dex */
public interface IUpdater {
    void updateProductContext(AIUpdateListener aIUpdateListener, ProductContext productContext);

    void updateSkillContext(AIUpdateListener aIUpdateListener, SkillContext skillContext);

    void updateVocabs(AIUpdateListener aIUpdateListener, Vocab... vocabArr);
}
