package com.aispeech.lite.dm.update;

import com.aispeech.export.ProductContext;
import com.aispeech.export.SkillContext;
import com.aispeech.export.Vocab;
/* loaded from: classes.dex */
public interface ICInfo {
    void uploadProductContext(ProductContext productContext);

    void uploadSkillContext(SkillContext skillContext);

    void uploadVocabs(Vocab... vocabArr);
}
