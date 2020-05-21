package io.dfjx.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getString(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? "" : CommonUtil.urlDecode(value);
    }
    public static String getString(HttpServletRequest request, String name, String defValue) {
        String value = request.getParameter(name);
        return CommonUtil.isNullOrEmpty(value) ? defValue : CommonUtil.urlDecode(value);
    }
    public static Integer getInteger(HttpServletRequest request, String name) {
        String str = request.getParameter(name);
        return CommonUtil.isNullOrEmpty(str) ? 0 : CommonUtil.convert(Integer.class, str);
    }
    public static Integer getInteger(HttpServletRequest request, String name, Integer defValue) {
        String str = request.getParameter(name);
        return CommonUtil.convert(Integer.class, str, defValue);
    }
    public static Long getLong(HttpServletRequest request, String name) {
        String str = request.getParameter(name);
        return CommonUtil.isNullOrEmpty(str) ? 0L : CommonUtil.convert(Long.class, str);
    }
    public static Long getLong(HttpServletRequest request, String name, Long defValue) {
        String str = request.getParameter(name);
        return CommonUtil.convert(Long.class, str, defValue);
    }
    
    public static String getRequestUrl(HttpServletRequest request) {
        String dir = request.getContextPath();
        String path = request.getRequestURI();
        path = path.substring(dir.length());
        if(request.getQueryString()!=null&&request.getQueryString().length()>0)
        path+= "?" + (request.getQueryString()); 
        return path;
    }

    public static String getResponseType(String type){
        String t = "";
        switch (type){
            case "json": t = "text/plain";
                break;
            case "xml": t = "text/xml";
                break;
            case "js": t = "application/x-javascript";
                break;
            default:
                t = "text/html";
                break;
        }
        return t;
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getDomain(){
        HttpServletRequest request = getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    public static String getOrigin(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }
}
