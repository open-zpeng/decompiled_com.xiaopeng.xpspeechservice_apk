package com.aispeech.c.a;

import android.text.TextUtils;
import com.aispeech.common.Log;
import com.aispeech.common.URLUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import okhttp3.Dns;
/* loaded from: classes.dex */
public final class b implements Dns {
    @Override // okhttp3.Dns
    public final List<InetAddress> lookup(String str) throws UnknownHostException {
        Log.i("DnsResolver", "hostname : " + str);
        if (URLUtils.isIP(str)) {
            Log.d("DnsResolver", str + " is ip, ignore dns resolve");
        } else {
            str = a.a(str);
        }
        Log.i("DnsResolver", "ip " + str);
        if (TextUtils.isEmpty(str)) {
            throw new UnknownHostException("dns resolve failed");
        }
        InetAddress[] allByName = InetAddress.getAllByName(str);
        for (InetAddress inetAddress : allByName) {
            Log.i("DnsResolver", "address : " + inetAddress.getHostAddress());
        }
        return Arrays.asList(allByName);
    }
}
