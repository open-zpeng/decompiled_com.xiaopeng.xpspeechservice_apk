package com.aispeech.upload.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
/* loaded from: classes.dex */
public class Headers {
    private final String[] namesAndValues;

    public Headers(Builder builder) {
        this.namesAndValues = (String[]) builder.namesAndValues.toArray(new String[builder.namesAndValues.size()]);
    }

    private Headers(String[] strArr) {
        this.namesAndValues = strArr;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        Collections.addAll(builder.namesAndValues, this.namesAndValues);
        return builder;
    }

    public int size() {
        return this.namesAndValues.length / 2;
    }

    public String name(int i) {
        return this.namesAndValues[i << 1];
    }

    public String value(int i) {
        return this.namesAndValues[(i << 1) + 1];
    }

    public String get(String str) {
        return get(this.namesAndValues, str);
    }

    public static Headers of(String... strArr) {
        if (strArr == null) {
            throw new NullPointerException("namesAndValues == null");
        }
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        for (int i = 0; i < strArr2.length; i++) {
            if (strArr2[i] == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i] = strArr2[i].trim();
        }
        for (int i2 = 0; i2 < strArr2.length; i2 += 2) {
            String str = strArr2[i2];
            String str2 = strArr2[i2 + 1];
            if (str.length() == 0 || str.indexOf(0) != -1 || str2.indexOf(0) != -1) {
                throw new IllegalArgumentException("Unexpected header: " + str + ": " + str2);
            }
        }
        return new Headers(strArr2);
    }

    private static String get(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public Set<String> names() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int size = size();
        for (int i = 0; i < size; i++) {
            treeSet.add(name(i));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private final List<String> namesAndValues = new ArrayList(20);

        final Builder addLenient(String str) {
            int indexOf = str.indexOf(":", 1);
            if (indexOf != -1) {
                return addLenient(str.substring(0, indexOf), str.substring(indexOf + 1));
            }
            if (str.startsWith(":")) {
                return addLenient("", str.substring(1));
            }
            return addLenient("", str);
        }

        public final Builder add(String str) {
            int indexOf = str.indexOf(":");
            if (indexOf == -1) {
                throw new IllegalArgumentException("Unexpected header: " + str);
            }
            return add(str.substring(0, indexOf).trim(), str.substring(indexOf + 1));
        }

        public final Builder add(String str, String str2) {
            checkNameAndValue(str, str2);
            return addLenient(str, str2);
        }

        final Builder addLenient(String str, String str2) {
            this.namesAndValues.add(str);
            this.namesAndValues.add(str2.trim());
            return this;
        }

        public final Builder removeAll(String str) {
            int i = 0;
            while (i < this.namesAndValues.size()) {
                if (str.equalsIgnoreCase(this.namesAndValues.get(i))) {
                    this.namesAndValues.remove(i);
                    this.namesAndValues.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public final Builder set(String str, String str2) {
            checkNameAndValue(str, str2);
            removeAll(str);
            addLenient(str, str2);
            return this;
        }

        private void checkNameAndValue(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            }
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt <= 31 || charAt >= 127) {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
                }
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            int length2 = str2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                char charAt2 = str2.charAt(i2);
                if (charAt2 <= 31 || charAt2 >= 127) {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2));
                }
            }
        }

        public final String get(String str) {
            for (int size = this.namesAndValues.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase(this.namesAndValues.get(size))) {
                    return this.namesAndValues.get(size + 1);
                }
            }
            return null;
        }

        public final Headers build() {
            return new Headers(this);
        }
    }
}
