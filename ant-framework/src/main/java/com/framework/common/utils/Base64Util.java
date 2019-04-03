package com.framework.common.utils;

import org.apache.commons.codec.binary.Base64;



/**
 * Description:BASE64 加密工具类
 * Auth: Frank
 * Date: 2017-10-26
 * Time: 下午 3:29
 */
public class Base64Util {

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key)  {
        return Base64.decodeBase64(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key)  {
        return Base64.encodeBase64String(key);
    }


}
