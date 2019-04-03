package com.framework.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:恺撒移位工具
 * 明文中的所有字母都在字母表上向后(或向前)按照一个固定数目进行偏移后被替换成密文。
 * 例如，当偏移量是3的时候，所有的字母A将被替换成D，B变成E，以此类推X将变成A，Y变成B，Z变成C。
 * 由此可见，位数就是凯撒密码加密和解密的密钥
 * Auth: Frank
 * Date: 2017-10-30
 * Time: 上午 11:21
 */
public class KaisaCodeUtil {
    private static final String NORMAL_SEQ_LOWERCASE  = "abcdefghijklmnopqrstuvwxyz";//小写字母
    private static final String NORMAL_SEQ_UPPERCASE  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//大写字母
    private static final String NORMAL_SEQ_NUMBER  = "0123456789";//数字

    /**
     * 加密
     * <p>
     *     加密规则：
     *     1，判断明文中是否含有{@link #NORMAL_SEQ_LOWERCASE}，{@link #NORMAL_SEQ_UPPERCASE}和{@link #NORMAL_SEQ_NUMBER}中的任意char，
     *     2，如果有，就逐步获得那些char，然后根据移动的位数分别找到{@link #NORMAL_SEQ_LOWERCASE}，{@link #NORMAL_SEQ_UPPERCASE}和{@link #NORMAL_SEQ_NUMBER}中移动后对应密文的char.
     *     3，合成密文
     * </p>
     * @param clearStr 明文
     * @param moveByte 移动位数
     * @return
     */
    public static String encodeKaisa(String clearStr, int moveByte){
        StringBuffer cipherSb = new StringBuffer();
        if(StringUtils.isBlank(clearStr)){
            return "";
        }
        int clearStrLength = clearStr.length();
        for(int i= 0;i<clearStrLength;i++){
            char clearChar = clearStr.charAt(i);
            boolean hasPos = false;//当前字符是否存在于NORMAL_SEQ_LOWERCASE或者NORMAL_SEQ_UPPERCASE

            //处理字符存在于NORMAL_SEQ_LOWERCASE情况
            int pos = NORMAL_SEQ_LOWERCASE.indexOf(clearChar);
            int lastPos = pos + moveByte;
            if(-1 != pos){
                if(lastPos<26) {
                    cipherSb.append(NORMAL_SEQ_LOWERCASE.charAt(lastPos));
                }else {
                    cipherSb.append(NORMAL_SEQ_LOWERCASE.charAt(lastPos-NORMAL_SEQ_LOWERCASE.length()));
                }
                hasPos = true;
            }
            if(hasPos){
                continue;
            }

            //处理字符存在于NORMAL_SEQ_UPPERCASE情况
            pos = NORMAL_SEQ_UPPERCASE.indexOf(clearChar);
            lastPos = pos + moveByte;
            if(-1 != pos){
                if(lastPos<26) {
                    cipherSb.append(NORMAL_SEQ_UPPERCASE.charAt(lastPos));
                }else {
                    cipherSb.append(NORMAL_SEQ_UPPERCASE.charAt(lastPos-NORMAL_SEQ_UPPERCASE.length()));
                }
                hasPos = true;
            }

            //处理字符存在于NORMAL_SEQ_NUMBER情况
            pos = NORMAL_SEQ_NUMBER.indexOf(clearChar);
            lastPos = pos + moveByte;
            if(-1 != pos){
                if(lastPos<10) {
                    cipherSb.append(NORMAL_SEQ_NUMBER.charAt(lastPos));
                }else {
                    cipherSb.append(NORMAL_SEQ_NUMBER.charAt(lastPos-NORMAL_SEQ_NUMBER.length()));
                }
                hasPos = true;
            }

            if(hasPos){
                continue;
            }else {
                cipherSb.append(clearChar);
            }
        }
        return cipherSb.toString();
    }

    /**
     * 解密
     * @param cipherStr 密文
     * @param moveByte 移动位数
     * @return
     */
    public static String decodeKaisa(String cipherStr, int moveByte){
        StringBuffer clearSb = new StringBuffer();
        if(StringUtils.isBlank(cipherStr)){
            return "";
        }
        int cipherStrLength = cipherStr.length();
        for(int i= 0;i<cipherStrLength;i++){
            char clearChar = cipherStr.charAt(i);
            boolean hasPos = false;//当前字符是否存在于NORMAL_SEQ_LOWERCASE或者NORMAL_SEQ_UPPERCASE

            //处理字符存在于NORMAL_SEQ_LOWERCASE情况
            int pos = NORMAL_SEQ_LOWERCASE.indexOf(clearChar);
            int lastPos = pos - moveByte;
            if(-1 != pos){
                if(lastPos >= 0) {
                    clearSb.append(NORMAL_SEQ_LOWERCASE.charAt(lastPos));
                }else {
                    clearSb.append(NORMAL_SEQ_LOWERCASE.charAt(lastPos+NORMAL_SEQ_LOWERCASE.length()));
                }
                hasPos = true;
            }
            if(hasPos){
                continue;
            }

            //处理字符存在于NORMAL_SEQ_UPPERCASE情况
            pos = NORMAL_SEQ_UPPERCASE.indexOf(clearChar);
            lastPos = pos - moveByte;
            if(-1 != pos){
                if(lastPos >= 0) {
                    clearSb.append(NORMAL_SEQ_UPPERCASE.charAt(lastPos));
                }else {
                    clearSb.append(NORMAL_SEQ_UPPERCASE.charAt(lastPos+NORMAL_SEQ_UPPERCASE.length()));
                }
                hasPos = true;
            }

            //处理字符存在于NORMAL_SEQ_NUMBER情况
            pos = NORMAL_SEQ_NUMBER.indexOf(clearChar);
            lastPos = pos - moveByte;
            if(-1 != pos){
                if(lastPos >= 0) {
                    clearSb.append(NORMAL_SEQ_NUMBER.charAt(lastPos));
                }else {
                    clearSb.append(NORMAL_SEQ_NUMBER.charAt(lastPos+NORMAL_SEQ_NUMBER.length()));
                }
                hasPos = true;
            }

            if(hasPos){
                continue;
            }else {
                clearSb.append(clearChar);
            }
        }
        return clearSb.toString();
    }

    public static void main(String[] args) {
        String cipherStr = encodeKaisa("6783$%#!~!#$%^&*&(FRANKfrank22411", 5);
        System.out.println(cipherStr);
        String clearStr = decodeKaisa(cipherStr,5);
        System.out.println(clearStr);
    }

}
