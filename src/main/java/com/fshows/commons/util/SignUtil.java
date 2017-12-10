package com.fshows.commons.util;


import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public final class SignUtil {

    /**
     * 参数根据ASCII码正序拼接 a=&b=&c=... 最后拼上key= 再对拼接后的字符串进行MD5 得到32位小写md5码
     */
    public static String sign(HashMap<String, Object> param, String secret) {
        if (param == null || param.isEmpty()) {
            return "";
        }
        param.remove("sign");
        param.remove("sign_type");


        // map类型转换
        SortedMap<String, String> signMap = new TreeMap<>();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            signMap.put(entry.getKey(), entry.getValue().toString());
        }

        StringBuilder original = new StringBuilder();

        for (Map.Entry<String, String> entry : signMap.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            original.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        original.append("key=").append(secret);

        return Md5.digest32Lower(original.toString());
    }

}
