package com.aispeech.common;

import java.util.HashMap;
/* loaded from: classes.dex */
public class URLUtils {
    public static final String HTTPS_PROTOCOL_HEAD = "https://";
    public static final String HTTP_PROTOCOL_HEAD = "http://";
    public static final String URL_DOMAIN_SEPERATOR = ".";
    public static final String URL_HTTP_HEAD = "http://";
    public static final String URL_PATH_SEPERATOR = "/";
    public static final String WSS_PROTOCOL_HEAD = "wss://";
    public static final String WS_PROTOCOL_HEAD = "ws://";
    public static final int HTTP_PROTOCOL_HEAD_LENGTH = 7;
    public static final int HTTPS_PROTOCOL_HEAD_LENGTH = 8;
    public static final int WSS_PROTOCOL_HEAD_LENGTH = 6;
    public static final int WS_PROTOCOL_HEAD_LENGTH = 5;
    private static final String[] a = {".com", ".biz", ".pro", ".aero", ".coop", ".museum", ".mobi", ".edu", ".gov", ".info", ".mil", ".name", ".net", ".org", ".jobs", ".travel", ".mil", ".arpa", ".int", ".cat", ".asia", ".tel"};
    private static final String[] b = {".com", ".net", ".edu"};
    private static final String[] c = {".ac", ".ad", ".ae", ".af", ".ag", ".ai", ".al", ".am", ".an", ".ao", ".aq", ".ar", ".as", ".at", ".au", ".aw", ".az", ".ba", ".bb", ".bd", ".be", ".bf", ".bg", ".bh", ".bi", ".bj", ".bm", ".bn", ".bo", ".br", ".bs", ".bt", ".bv", ".bw", ".by", ".bz", ".ca", ".cc", ".cd", ".cf", ".cg", ".ch", ".ci", ".ck", ".cl", ".cm", ".cn", ".co", ".cr", ".cs", ".cu", ".cv", ".cx", ".cy", ".cz", ".de", ".dj", ".dk", ".dm", ".do", ".dz", ".ec", ".eu", ".fi", ".fj", ".fk", ".fm", ".fo", ".fr", ".fx", ".ga", ".gb", ".gd", ".ge", ".gf", ".gh", ".gi", ".gl", ".gp", ".gq", ".gf", ".gm", ".gn", ".gr", ".gs", ".gt", ".gu", ".gw", ".gy", ".hk", ".hm", ".hn", ".hr", ".ht", ".hu", ".id", ".ie", ".il", ".in", ".io", ".iq", ".ir", ".is", ".it", ".jm", ".jo", ".jp", ".ke", ".kg", ".kh", ".ki", ".km", ".kn", ".kp", ".kr", ".kw", ".ky", ".kz", ".la", ".lb", ".lc", ".li", ".lk", ".lr", ".ls", ".lt", ".lu", ".lv", ".ly", ".ma", ".mc", ".md", ".mg", ".mh", ".mk", ".ml", ".mm", ".mn", ".mo", ".mp", ".mq", ".mr", ".ms", ".mt", ".mu", ".mv", ".mw", ".mx", ".my", ".mz", ".na", ".nc", ".ne", ".nf", ".ng", ".ni", ".nl", ".no", ".np", ".nr", ".nt", ".nu", ".nz", ".om", ".pa", ".pe", ".pf", ".pg", ".ph", ".pk", ".pl", ".pm", ".pn", ".pt", ".pr", ".pw", ".py", ".qa", ".re", ".ro", ".ru", ".rw", ".sa", ".sb", ".sc", ".sd", ".se", ".sg", ".sh", ".si", ".sj", ".sk", ".sl", ".sm", ".sn", ".so", ".sr", ".st", ".su", ".sv", ".sy", ".sz", ".tc", ".td", ".tf", ".tg", ".th", ".tj", ".tk", ".tm", ".tn", ".to", ".tp", ".tr", ".tt", ".tv", ".tw", ".tz", ".ua", ".ug", ".uk", ".um", ".us", ".uy", ".uz", ".va", ".vc", ".ve", ".vg", ".vi", ".vn", ".vu", ".wf", ".ws", ".ye", ".yt", ".yu", ".za", ".zm", ".zr", ".zw", ".ad", ".ae", ".af", ".ag", ".ai", ".al", ".am", ".an", ".ao", ".aq", ".ar", ".as", ".at", ".au", ".aw", ".az", ".ba", ".bb", ".bd", ".be", ".bf", ".bg", ".bh", ".bi", ".bj", ".bm", ".bn", ".bo", ".br", ".bs", ".bt", ".bv", ".bw", ".by", ".bz", ".ca", ".cc", ".cf", ".cg", ".ch", ".ci", ".ck", ".cl", ".cm", ".cn", ".co", ".cq", ".cr", ".cu", ".cv", ".cx", ".cy", ".cz", ".de", ".dj", ".dk", ".dm", ".do", ".dz", ".ec", ".ee", ".eg", ".eh", ".es", ".et", ".ev", ".fi", ".fj", ".fk", ".fm", ".fo", ".fr", ".ga", ".gb", ".gd", ".ge", ".gf", ".gh", ".gi", ".gl", ".gm", ".gn", ".gp", ".gr", ".gt", ".gu", ".gw", ".gy", ".hk", ".hm", ".hn", ".hr", ".ht", ".hu", ".id", ".ie", ".il", ".in", ".io", ".iq", ".ir", ".is", ".it", ".jm", ".jo", ".jp", ".ke", ".kg", ".kh", ".ki", ".km", ".kn", ".kp", ".kr", ".kw", ".ky", ".kz", ".la", ".lb", ".lc", ".li", ".lk", ".lr", ".ls", ".lt", ".lu", ".lv", ".ly", ".ma", ".mc", ".md", ".me", ".mg", ".mh", ".ml", ".mm", ".mn", ".mo", ".mp", ".mq", ".mr", ".ms", ".mt", ".mv", ".mw", ".mx", ".my", ".mz", ".na", ".nc", ".ne", ".nf", ".ng", ".ni", ".nl", ".no", ".np", ".nr", ".nt", ".nu", ".nz", ".om", ".pa", ".pe", ".pf", ".pg", ".ph", ".pk", ".pl", ".pm", ".pn", ".pr", ".pt", ".pw", ".py", ".qa", ".re", ".ro", ".rs", ".ru", ".rw", ".sa", ".sb", ".sc", ".sd", ".se", ".sg", ".sh", ".si", ".sj", ".sk", ".sl", ".sm", ".sn", ".so", ".sr", ".st", ".su", ".sy", ".sz", ".tc", ".td", ".tf", ".tg", ".th", ".tj", ".tk", ".tl", ".tm", ".tn", ".to", ".tp", ".tr", ".tt", ".tv", ".tw", ".tz", ".ua", ".ug", ".uk", ".us", ".uy", ".va", ".vc", ".ve", ".vg", ".vn", ".vu", ".wf", ".ws", ".ye", ".yu", ".za", ".zm", ".zr", ".zw"};
    private static final String[] d = {".cn", ".bj", ".id", ".co", ".il", ".co", ".jp", ".co", ".kr", ".co", ".nr", ".co", ".uk", ".co", ".uz", ".co", ".cn", ".ac", ".cn", ".com", ".cn", ".edu", ".cn", ".gov", ".cn", ".net", ".cn", ".org", ".cn", ".sh", ".cn", ".tj", ".cn", ".cq", ".cn", ".he", ".cn", ".sx", ".cn", ".nm", ".cn", ".ln", ".cn", ".jl", ".cn", ".hl", ".cn", ".js", ".cn", ".zj", ".cn", ".ah", ".cn", ".fj", ".cn", ".jx", ".cn", ".sd", ".cn", ".ha", ".cn", ".hb", ".cn", ".hn", ".cn", ".gd", ".cn", ".gx", ".cn", ".hi", ".cn", ".sc", ".cn", ".gz", ".cn", ".yn", ".cn", ".xz", ".cn", ".sn", ".cn", ".gs", ".cn", ".qh", ".cn", ".nx", ".cn", ".xj", ".cn", ".tw", ".cn", ".hk", ".cn", ".mo", ".ru", ".net"};
    public static HashMap<String, HashMap<String, Object>> urlPostfixMap = new HashMap<>();
    public static HashMap<String, String> regionalUrlPostfixMap = new HashMap<>();
    public static HashMap<String, String> traditionalUrlPostfixMap = new HashMap<>();
    public static HashMap<String, HashMap<String, Object>> urlPostfixMap_noDot = new HashMap<>();
    public static HashMap<String, String> regionalUrlPostfixMap_noDot = new HashMap<>();
    public static HashMap<String, String> traditionalUrlPostfixMap_noDot = new HashMap<>();

    static {
        for (int i = 0; i < 22; i++) {
            String[] strArr = a;
            if (strArr[i] != null) {
                String trim = strArr[i].trim();
                traditionalUrlPostfixMap.put(trim, null);
                urlPostfixMap.put(trim, null);
                if (trim.length() > 0) {
                    String substring = trim.substring(1);
                    traditionalUrlPostfixMap_noDot.put(substring, null);
                    urlPostfixMap_noDot.put(substring, null);
                }
            }
        }
        for (int i2 = 0; i2 < 477; i2++) {
            String[] strArr2 = c;
            if (strArr2[i2] != null) {
                String trim2 = strArr2[i2].trim();
                regionalUrlPostfixMap.put(trim2, null);
                urlPostfixMap.put(trim2, null);
                String[] strArr3 = b;
                HashMap<String, Object> hashMap = urlPostfixMap.get(trim2);
                for (int i3 = 0; i3 < 3; i3++) {
                    String str = strArr3[i3];
                    if (hashMap == null) {
                        hashMap = new HashMap<>();
                        urlPostfixMap.put(trim2, hashMap);
                    }
                    hashMap.put(str, null);
                }
                if (trim2.length() > 0) {
                    String substring2 = trim2.substring(1);
                    regionalUrlPostfixMap_noDot.put(substring2.substring(1), null);
                    urlPostfixMap_noDot.put(substring2, null);
                }
            }
        }
        for (int i4 = 0; i4 < 96; i4 += 2) {
            int i5 = i4 + 1;
            if (i5 < 96) {
                String[] strArr4 = d;
                String str2 = strArr4[i4];
                String str3 = strArr4[i5];
                HashMap<String, Object> hashMap2 = urlPostfixMap.get(str2);
                if (hashMap2 == null) {
                    hashMap2 = new HashMap<>();
                    urlPostfixMap.put(str2, hashMap2);
                }
                hashMap2.put(str3, null);
                if (str2.length() > 0 && str3.length() > 0) {
                    String substring3 = str2.substring(1);
                    String substring4 = str3.substring(1);
                    HashMap<String, Object> hashMap3 = urlPostfixMap_noDot.get(substring3);
                    if (hashMap3 == null) {
                        hashMap3 = new HashMap<>();
                        urlPostfixMap_noDot.put(substring3, hashMap3);
                    }
                    hashMap3.put(substring4, null);
                }
            } else {
                return;
            }
        }
    }

    public static boolean isLetterOrDigit(char c2) {
        if ('0' > c2 || c2 > '9') {
            if ('a' > c2 || c2 > 'z') {
                return 'A' <= c2 && c2 <= 'Z';
            }
            return true;
        }
        return true;
    }

    public static String getDomain(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        if (str.startsWith("http://")) {
            i = HTTP_PROTOCOL_HEAD_LENGTH;
        } else if (str.startsWith(HTTPS_PROTOCOL_HEAD)) {
            i = HTTPS_PROTOCOL_HEAD_LENGTH;
        } else if (str.startsWith(WSS_PROTOCOL_HEAD)) {
            i = WSS_PROTOCOL_HEAD_LENGTH;
        } else if (str.startsWith(WS_PROTOCOL_HEAD)) {
            i = WS_PROTOCOL_HEAD_LENGTH;
        }
        int indexOf = str.indexOf(47, i);
        if (indexOf > 0) {
            return str.substring(i, indexOf);
        }
        if (i > 0) {
            return str.substring(i);
        }
        return str;
    }

    public static String getDomainWithoutPort(String str) {
        String domain = getDomain(str);
        if (domain == null) {
            return null;
        }
        int indexOf = domain.indexOf(58);
        if (indexOf > 0) {
            return domain.substring(0, indexOf);
        }
        return domain;
    }

    public static String getDomainWithProtocal(String str) {
        int i;
        if (str == null) {
            return null;
        }
        if (str.startsWith("http://")) {
            i = HTTP_PROTOCOL_HEAD_LENGTH;
        } else if (!str.startsWith(HTTPS_PROTOCOL_HEAD)) {
            i = 0;
        } else {
            i = HTTPS_PROTOCOL_HEAD_LENGTH;
        }
        int indexOf = str.indexOf(47, i);
        if (indexOf > 0) {
            return str.substring(0, indexOf);
        }
        return str;
    }

    public static String regulateUrl(String str) {
        boolean z;
        if (str == null) {
            return null;
        }
        boolean z2 = true;
        if (!str.startsWith("http://") && !str.startsWith(HTTPS_PROTOCOL_HEAD)) {
            z = true;
        } else {
            z = false;
        }
        if (!isDomain(str) || str.endsWith(URL_PATH_SEPERATOR)) {
            z2 = false;
        }
        if (z) {
            str = "http://" + str;
        }
        if (z2) {
            return str + '/';
        }
        return str;
    }

    public static String getLookupUrl(String str) {
        String substring;
        int i;
        int i2;
        String str2;
        String validateDomain;
        String trim = str.trim();
        String lowerCase = trim.toLowerCase();
        String str3 = "http://";
        if (lowerCase.startsWith("http://")) {
            trim = trim.substring(HTTP_PROTOCOL_HEAD_LENGTH);
        } else if (lowerCase.startsWith(HTTPS_PROTOCOL_HEAD)) {
            trim = trim.substring(HTTPS_PROTOCOL_HEAD_LENGTH);
            str3 = HTTPS_PROTOCOL_HEAD;
        }
        int indexOf = trim.indexOf(47);
        int indexOf2 = trim.indexOf(58);
        if (indexOf < 0) {
            if (indexOf2 > 0) {
                try {
                    i2 = Integer.parseInt(trim.substring(indexOf2 + 1));
                    trim = trim.substring(0, indexOf2);
                } catch (NumberFormatException e) {
                    return null;
                }
            } else {
                i2 = 80;
            }
            str2 = URL_PATH_SEPERATOR;
        } else {
            if (indexOf2 > 0 && indexOf2 < indexOf) {
                try {
                    i = Integer.parseInt(trim.substring(indexOf2 + 1, indexOf));
                    substring = trim.substring(0, indexOf2);
                } catch (NumberFormatException e2) {
                    return null;
                }
            } else {
                substring = trim.substring(0, indexOf);
                i = 80;
            }
            String substring2 = trim.substring(indexOf);
            i2 = i;
            String str4 = substring;
            str2 = substring2;
            trim = str4;
        }
        if (i2 <= 0 || i2 > 65535 || (validateDomain = validateDomain(trim)) == null) {
            return null;
        }
        if (i2 == 80) {
            return str3 + validateDomain + str2;
        }
        return str3 + validateDomain + ':' + i2 + str2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0027, code lost:
        if (r1.hasMoreTokens() != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0029, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isIP(java.lang.String r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            java.util.StringTokenizer r1 = new java.util.StringTokenizer     // Catch: java.lang.NumberFormatException -> L2b java.util.NoSuchElementException -> L2d
            java.lang.String r2 = "."
            r1.<init>(r5, r2)     // Catch: java.lang.NumberFormatException -> L2b java.util.NoSuchElementException -> L2d
            r5 = r0
        Ld:
            r2 = 4
            if (r5 >= r2) goto L21
            java.lang.String r3 = r1.nextToken()     // Catch: java.lang.NumberFormatException -> L2b java.util.NoSuchElementException -> L2d
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L2b java.util.NoSuchElementException -> L2d
            if (r3 < 0) goto L21
            r4 = 255(0xff, float:3.57E-43)
            if (r3 > r4) goto L21
            int r5 = r5 + 1
            goto Ld
        L21:
            if (r5 != r2) goto L2a
            boolean r5 = r1.hasMoreTokens()     // Catch: java.lang.NumberFormatException -> L2b java.util.NoSuchElementException -> L2d
            if (r5 != 0) goto L2a
            r0 = 1
        L2a:
            goto L2f
        L2b:
            r5 = move-exception
            goto L2f
        L2d:
            r5 = move-exception
            goto L2a
        L2f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.common.URLUtils.isIP(java.lang.String):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x00ad, code lost:
        if (com.aispeech.common.URLUtils.urlPostfixMap.containsKey(r9.substring(r9.lastIndexOf(46))) != false) goto L76;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b9 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String validateDomain(java.lang.String r9) {
        /*
            r0 = 0
            if (r9 != 0) goto L4
            return r0
        L4:
            r1 = 0
            r2 = r1
        L6:
            int r3 = r9.length()
            r4 = 46
            r5 = 1
            if (r2 >= r3) goto L3e
            char r3 = r9.charAt(r2)
            r6 = 127(0x7f, float:1.78E-43)
            if (r3 <= r6) goto L18
            return r0
        L18:
            boolean r6 = isLetterOrDigit(r3)
            if (r6 != 0) goto L3b
            if (r3 != r4) goto L29
            if (r2 == 0) goto L29
            int r4 = r9.length()
            int r4 = r4 - r5
            if (r2 != r4) goto L3b
        L29:
            r4 = 45
            if (r3 == r4) goto L31
            r4 = 95
            if (r3 != r4) goto L3a
        L31:
            if (r2 == 0) goto L3a
            int r3 = r9.length()
            int r3 = r3 - r5
            if (r2 != r3) goto L3b
        L3a:
            return r0
        L3b:
            int r2 = r2 + 1
            goto L6
        L3e:
            boolean r2 = isIP(r9)
            if (r2 == 0) goto L47
        L45:
            goto Lb6
        L47:
            java.util.StringTokenizer r2 = new java.util.StringTokenizer
            java.lang.String r3 = "."
            r2.<init>(r9, r3)
        L4f:
            boolean r6 = r2.hasMoreTokens()
            if (r6 == 0) goto L8c
            java.lang.String r6 = r2.nextToken()
            int r7 = r6.length()
            if (r7 == 0) goto L89
            boolean r7 = r6.startsWith(r3)
            if (r7 != 0) goto L89
            boolean r7 = r6.endsWith(r3)
            if (r7 != 0) goto L89
            java.lang.String r7 = "-"
            boolean r8 = r6.startsWith(r7)
            if (r8 != 0) goto L89
            boolean r7 = r6.endsWith(r7)
            if (r7 != 0) goto L89
            java.lang.String r7 = "_"
            boolean r8 = r6.startsWith(r7)
            if (r8 != 0) goto L89
            boolean r6 = r6.endsWith(r7)
            if (r6 == 0) goto L88
            goto L89
        L88:
            goto L4f
        L89:
            r2 = r1
            goto L8d
        L8c:
            r2 = r5
        L8d:
            if (r2 == 0) goto L98
            java.lang.String r3 = ".."
            int r3 = r9.indexOf(r3)
            if (r3 < 0) goto L98
            r2 = r1
        L98:
            if (r2 == 0) goto Lb5
        L9b:
            java.lang.String r9 = r9.toLowerCase()
            int r2 = r9.lastIndexOf(r4)
            java.lang.String r2 = r9.substring(r2)     // Catch: java.lang.IndexOutOfBoundsException -> Lb2
            java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, java.lang.Object>> r3 = com.aispeech.common.URLUtils.urlPostfixMap     // Catch: java.lang.IndexOutOfBoundsException -> Lb2
            boolean r2 = r3.containsKey(r2)     // Catch: java.lang.IndexOutOfBoundsException -> Lb2
            if (r2 == 0) goto Lb1
        Lb0:
            goto Lb6
        Lb1:
            goto Lb3
        Lb2:
            r2 = move-exception
        Lb3:
            r5 = r1
            goto Lb6
        Lb5:
            r5 = r2
        Lb6:
            if (r5 == 0) goto Lb9
            return r9
        Lb9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aispeech.common.URLUtils.validateDomain(java.lang.String):java.lang.String");
    }

    public static boolean isDomain(String str) {
        int i;
        if (str.startsWith("http://")) {
            i = HTTP_PROTOCOL_HEAD_LENGTH;
        } else if (!str.startsWith(HTTPS_PROTOCOL_HEAD)) {
            i = 0;
        } else {
            i = HTTPS_PROTOCOL_HEAD_LENGTH;
        }
        int indexOf = str.indexOf(47, i);
        return indexOf < 0 || indexOf == str.length() - 1;
    }

    public static String getMainDomain(String str) {
        int lastIndexOf;
        String domainWithoutPort = getDomainWithoutPort(str);
        if (domainWithoutPort == null) {
            return null;
        }
        HashMap<String, Object> hashMap = urlPostfixMap;
        int length = domainWithoutPort.length();
        while (true) {
            lastIndexOf = domainWithoutPort.lastIndexOf(46, length - 1);
            if (lastIndexOf < 0 || hashMap == null) {
                break;
            }
            String substring = domainWithoutPort.substring(lastIndexOf, length);
            if (!hashMap.containsKey(substring)) {
                break;
            }
            hashMap = hashMap.get(substring);
            length = lastIndexOf;
        }
        if (length == domainWithoutPort.length()) {
            return null;
        }
        if (lastIndexOf < 0) {
            return domainWithoutPort;
        }
        return domainWithoutPort.substring(lastIndexOf + 1);
    }

    public static boolean isNonWWW(String str) {
        String mainDomain;
        String domainWithoutPort = getDomainWithoutPort(str);
        return (domainWithoutPort == null || (mainDomain = getMainDomain(domainWithoutPort)) == null || !mainDomain.equals(domainWithoutPort)) ? false : true;
    }

    public static String getQueryString(String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(63)) < 0) {
            return null;
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(35, i);
        if (indexOf2 < 0) {
            return str.substring(i);
        }
        return str.substring(i, indexOf2);
    }

    public static String getParameter(String str, String str2) {
        int indexOf;
        int length;
        int i;
        if (str == null || str2 == null) {
            return null;
        }
        String str3 = str2 + "=";
        int indexOf2 = str.indexOf("/#");
        if (indexOf2 >= 0) {
            indexOf = str.indexOf(35, indexOf2 + 2);
        } else {
            indexOf = str.indexOf(35);
        }
        if (indexOf < 0) {
            indexOf = str.length();
        }
        int i2 = -1;
        do {
            i2 = str.indexOf(str3, i2 + 1);
            if (i2 >= 0) {
                if (i2 != 0) {
                    i = i2 - 1;
                    if (str.charAt(i) == '?' || str.charAt(i) == '&') {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                return null;
            }
        } while (str.charAt(i) != '#');
        if (i2 < 0 || (length = i2 + str3.length()) >= indexOf) {
            return null;
        }
        int indexOf3 = str.indexOf(38, length + 1);
        if (indexOf3 <= 0 || indexOf3 >= indexOf) {
            indexOf3 = indexOf;
        }
        return str.substring(length, indexOf3);
    }
}
