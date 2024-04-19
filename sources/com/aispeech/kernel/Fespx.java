package com.aispeech.kernel;
/* loaded from: classes.dex */
public abstract class Fespx {

    /* loaded from: classes.dex */
    public interface doa_callback {
        int run(int i, byte[] bArr, int i2);
    }

    /* loaded from: classes.dex */
    public interface wakeup_callback {
        int run(int i, byte[] bArr, int i2);
    }

    /* loaded from: classes.dex */
    public static class beamforming_callback {
        public static byte[] bufferData = new byte[3200];

        public int run(int i, byte[] bArr, int i2) {
            return 0;
        }

        public static byte[] getBufferData() {
            return bufferData;
        }
    }

    /* loaded from: classes.dex */
    public static class vprintcut_callback {
        public static byte[] bufferData = new byte[3200];

        public int run(int i, byte[] bArr, int i2) {
            return 0;
        }

        public static byte[] getBufferData() {
            return bufferData;
        }
    }
}
