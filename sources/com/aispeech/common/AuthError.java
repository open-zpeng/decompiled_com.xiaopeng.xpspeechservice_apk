package com.aispeech.common;

import android.text.TextUtils;
/* loaded from: classes.dex */
public class AuthError {
    public static final String CANNOT_GET_VALID_PROFILE = "070602";
    public static final String EXCEEDED_MAX_TRIALS = "070615";
    public static final String INVALID_APIKEY = "070603";
    public static final String INVALID_CERTIFICATION = "070612";
    public static final String INVALID_PRODUCTID = "070604";
    public static final String INVALID_SHA256 = "070611";
    public static final String INVALID_SIGNATURE = "070614";
    public static final String NETWORK_CONNECT_TIMEOUT = "070613";
    public static final String NETWORK_ERROR = "070601";
    public static final String PROFILE_DISABLED = "070606";
    public static final String PROFILE_EXPIRED = "070607";
    public static final String PROFILE_ILLEGAL_FOR_DEVICE = "070608";
    public static final String PROFILE_ILLEGAL_FOR_PRODUCTID = "070610";
    public static final String READ_PROFILE_FAILED = "070605";
    public static final String WRITE_PROFILE_FAILED = "070609";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getMessage(String str) {
        char c;
        int hashCode = str.hashCode();
        switch (hashCode) {
            case 1426476302:
                if (str.equals(NETWORK_ERROR)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1426476303:
                if (str.equals(CANNOT_GET_VALID_PROFILE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1426476304:
                if (str.equals(INVALID_APIKEY)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1426476305:
                if (str.equals(INVALID_PRODUCTID)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1426476306:
                if (str.equals(READ_PROFILE_FAILED)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1426476307:
                if (str.equals(PROFILE_DISABLED)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1426476308:
                if (str.equals(PROFILE_EXPIRED)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1426476309:
                if (str.equals(PROFILE_ILLEGAL_FOR_DEVICE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1426476310:
                if (str.equals(WRITE_PROFILE_FAILED)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                switch (hashCode) {
                    case 1426476332:
                        if (str.equals(PROFILE_ILLEGAL_FOR_PRODUCTID)) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1426476333:
                        if (str.equals(INVALID_SHA256)) {
                            c = '\n';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1426476334:
                        if (str.equals(INVALID_CERTIFICATION)) {
                            c = 11;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1426476335:
                        if (str.equals(NETWORK_CONNECT_TIMEOUT)) {
                            c = '\f';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1426476336:
                        if (str.equals(INVALID_SIGNATURE)) {
                            c = '\r';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1426476337:
                        if (str.equals(EXCEEDED_MAX_TRIALS)) {
                            c = 14;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
        }
        switch (c) {
            case 0:
                return "network abnormal, can not connect to auth server";
            case 1:
                return "can not get valid profile";
            case 2:
                return "invalid api key";
            case 3:
                return "Invalid product id";
            case 4:
                return "read provision file failed";
            case 5:
                return "profile file is disabled";
            case 6:
                return "profile file is expired";
            case 7:
                return "profile file is illegal for this device";
            case '\b':
                return "can not save profile";
            case '\t':
                return "profile file is illegal for this productId";
            case '\n':
                return "invalid api key or the runtime SHA256 does not match the SHA256 when configuring apikey information";
            case 11:
                return "invalid certification";
            case '\f':
                return "connect server timeout";
            case '\r':
                return "invalid productKey or productSecret";
            case 14:
                return "exceed the max number of trials";
            default:
                return "无法预知的错误发生了，请在www.dui.ai上联系我们.";
        }
    }

    /* loaded from: classes.dex */
    public enum AUTH_ERR_MSG {
        ERR_SDK_NO_INIT("070900", "SDK no init"),
        ERR_NET_CONNECT(AuthError.NETWORK_ERROR, "network abnormal, can not connect to auth server"),
        ERR_PROFILE_GET(AuthError.CANNOT_GET_VALID_PROFILE, "can not get valid profile"),
        ERR_API_KEY_INVALID(AuthError.INVALID_APIKEY, "invalid api key"),
        ERR_PRODUCT_ID_INVALID(AuthError.INVALID_PRODUCTID, "Invalid product id"),
        ERR_PROFILE_READ(AuthError.READ_PROFILE_FAILED, "read provision file failed"),
        ERR_PROFILE_DISABLED(AuthError.PROFILE_DISABLED, "profile file is disabled"),
        ERR_PROFILE_EXPIRED(AuthError.PROFILE_EXPIRED, "profile file is expired"),
        ERR_PROFILE_NO_MATCH_DEVICE(AuthError.PROFILE_ILLEGAL_FOR_DEVICE, "profile file is illegal for this device"),
        ERR_PROFILE_SAVE(AuthError.WRITE_PROFILE_FAILED, "can not save profile"),
        ERR_PROFILE_NO_MACTH_PRODUCT_ID(AuthError.PROFILE_ILLEGAL_FOR_PRODUCTID, "profile file is illegal for this productId"),
        ERR_SHA256_INVALID(AuthError.INVALID_SHA256, "invalid api key or the runtime SHA256 does not match the SHA256 when configuring apikey information"),
        ERR_CERTIFICATION_INVALID(AuthError.INVALID_CERTIFICATION, "invalid certification"),
        ERR_NET_TIMEOUT(AuthError.NETWORK_CONNECT_TIMEOUT, "connect server timeout"),
        ERR_PRODUCT_KEY_INVALID(AuthError.INVALID_SIGNATURE, "invalid productKey or productSecret"),
        ERR_EXCEED_MAX_TRIAL_NUM(AuthError.EXCEEDED_MAX_TRIALS, "exceed the max number of trials"),
        ERR_PROFILE_SCOPE("070616", "invalid scope in profile"),
        ERR_PROFILE_NO_EXIST("070629", "profile no exist"),
        ERR_DEFALUT("070630", "无法预知的错误发生了，请在www.dui.ai上联系我们."),
        ERR_SERVER_070631("070631", "201900", "请求参数非法，参数不全、不在值域内等"),
        ERR_SERVER_070632("070632", "201901", "非法的Licence 或 license已被使用"),
        ERR_SERVER_070633("070633", "201902", "Licence 已经被使用"),
        ERR_SERVER_070634("070634", "201903", "非android、ios设备，未找到设备唯一性字段"),
        ERR_SERVER_070635("070635", "201904", "当前设备激活过，不要再次使用license进行激活（此次传入的license并没有被消费）"),
        ERR_SERVER_070636("070636", "201905", "未激活过的设备，需要使用license进行激活"),
        ERR_SERVER_070637("070637", "201906", "设置的唯一性校验字段未全部赋值"),
        ERR_SERVER_070638("070638", "201907", "唯一性校验字段与初始值不一致"),
        ERR_SERVER_070639("070639", "201908", "productSecret在数据库中不存在"),
        ERR_SERVER_070640("070640", "201909", "校验码signature不正确，直接原因为秘钥错误"),
        ERR_SERVER_070641("070641", "201910", "product key 与productId不匹配"),
        ERR_SERVER_070642("070642", "201911", "数据库中不存在该apikey"),
        ERR_SERVER_070643("070643", "201912", "apikey和产品不匹配"),
        ERR_SERVER_070644("070644", "201913", "设备校验太过频繁"),
        ERR_SERVER_070645("070645", "201914", "未设置产品的授权类型"),
        ERR_SERVER_070646("070646", "201915", "未提供且无法生成设备名信息（仅Android、IOS可根据请求信息生成）"),
        ERR_SERVER_070647("070647", "201916", "无效的设备名，即该产品下不存在该设备名"),
        ERR_SERVER_070648("070648", "201917", "产品的设备被禁用"),
        ERR_SERVER_070649("070649", "201918", "产品的新设备激活被禁用，允许激活过的设备激活"),
        ERR_SERVER_070650("070650", "201919", "未找到设备的secret信息，未激活时提示该错误"),
        ERR_SERVER_070651("070651", "201920", "设备设置了过期时间，且已过期"),
        ERR_SERVER_070652("070652", "201921", "无效的apikey"),
        ERR_SERVER_070653("070653", "201922", "apikey与其类型对应的校验信息不匹配"),
        ERR_SERVER_070654("070654", "201923", "设备首次激活时出现并发激活"),
        ERR_SERVER_070655("070655", "201924", "产品设置为强管控模式，未设置可激活量或可激活量小于等于已激活量"),
        ERR_SERVER_070656("070656", "201925", "产品被禁用"),
        ERR_SERVER_070657("070657", "201926", "设备被禁用"),
        ERR_SERVER_070658("070658", "201927", "设备的激活次数被限制，包括仅首次激活有效、产品级与设备级重复激活次数耗尽"),
        ERR_SERVER_070659("070659", "201928", "签名错误，客户端需要重新激活");
        
        private final String a;
        private final String b;
        private final String c;

        public final String getValue() {
            return this.a;
        }

        public final String getId() {
            return this.b;
        }

        public final String getServerErrorId() {
            return this.c;
        }

        AUTH_ERR_MSG(String str, String str2) {
            this.b = str;
            this.c = "";
            this.a = str2;
        }

        AUTH_ERR_MSG(String str, String str2, String str3) {
            this.b = str;
            this.c = str2;
            this.a = str3;
        }

        public final boolean isNewError() {
            return this.c.length() > 0;
        }

        public static AUTH_ERR_MSG parseServerErrorId(String str) {
            AUTH_ERR_MSG[] auth_err_msgArr;
            if (TextUtils.isEmpty(str)) {
                return ERR_PRODUCT_KEY_INVALID;
            }
            for (AUTH_ERR_MSG auth_err_msg : (AUTH_ERR_MSG[]) AUTH_ERR_MSG.class.getEnumConstants()) {
                if (!TextUtils.isEmpty(auth_err_msg.getServerErrorId()) && auth_err_msg.getServerErrorId().equals(str)) {
                    return auth_err_msg;
                }
            }
            return ERR_PRODUCT_KEY_INVALID;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "AUTH_ERR_MSG{value='" + this.a + "', id='" + this.b + "', serverErrorId='" + this.c + "'}";
        }
    }
}
