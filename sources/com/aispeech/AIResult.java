package com.aispeech;

import android.os.Parcel;
import android.os.Parcelable;
import com.aispeech.common.Util;
/* loaded from: classes.dex */
public class AIResult implements Parcelable {
    public static final Parcelable.Creator<AIResult> CREATOR = new Parcelable.Creator<AIResult>() { // from class: com.aispeech.AIResult.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ AIResult[] newArray(int i) {
            return new AIResult[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ AIResult createFromParcel(Parcel parcel) {
            return new AIResult(parcel, (byte) 0);
        }
    };
    private int a;
    private boolean b;
    public int dataType;
    public boolean last;
    public String recordId;
    public Object resultObject;
    public long timestamp;

    /* synthetic */ AIResult(Parcel parcel, byte b) {
        this(parcel);
    }

    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String str) {
        this.recordId = str;
    }

    public Object getResultObject() {
        return this.resultObject;
    }

    public void setResultObject(Object obj) {
        this.resultObject = obj;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public boolean isLast() {
        return this.last;
    }

    public void setLast(boolean z) {
        this.last = z;
    }

    public int getResultType() {
        return this.dataType;
    }

    public void setResultType(int i) {
        this.dataType = i;
    }

    public void setPreResult(boolean z) {
        this.b = z;
    }

    public boolean isPreResult() {
        return this.b;
    }

    public String toString() {
        return this.resultObject.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.dataType);
        parcel.writeByte(this.last ? (byte) 1 : (byte) 0);
        parcel.writeString(this.recordId);
        parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
        if (this.dataType == 0) {
            parcel.writeString((String) this.resultObject);
        } else {
            this.a = ((byte[]) this.resultObject).length;
            parcel.writeInt(this.a);
            parcel.writeByteArray((byte[]) this.resultObject);
        }
        parcel.writeLong(this.timestamp);
    }

    private AIResult(Parcel parcel) {
        this.last = false;
        this.b = false;
        this.dataType = parcel.readInt();
        this.last = parcel.readByte() == 1;
        this.recordId = parcel.readString();
        this.b = parcel.readByte() == 1;
        if (this.dataType == 0) {
            this.resultObject = parcel.readString();
        } else {
            this.a = parcel.readInt();
            this.resultObject = new byte[this.a];
            parcel.readByteArray((byte[]) this.resultObject);
        }
        this.timestamp = parcel.readLong();
    }

    public AIResult() {
        this.last = false;
        this.b = false;
    }

    public static AIResult bundleResults(int i, String str, byte[] bArr) {
        AIResult aIResult = new AIResult();
        Object obj = bArr;
        if (i == 0) {
            obj = Util.newUTF8String(bArr);
        }
        aIResult.setResultObject(obj);
        aIResult.setRecordId(str);
        aIResult.setTimestamp(System.currentTimeMillis());
        aIResult.setResultType(i);
        return aIResult;
    }

    public static AIResult bundleResults(int i, String str, String str2) {
        AIResult aIResult = new AIResult();
        aIResult.setResultObject(str2);
        aIResult.setRecordId(str);
        aIResult.setTimestamp(System.currentTimeMillis());
        aIResult.setResultType(i);
        return aIResult;
    }
}
