package io.dfjx.common.util;

import java.util.Map;

public class MapUtil {

    public static String mstr(Map<String, Object> params, String key){
        if(!params.containsKey(key))
            return "";
        Object o = params.get(key);
        return o == null ? "" : o.toString();
    }
    public static String str(Object value){
        return value == null ? "" : value.toString();
    }
}
