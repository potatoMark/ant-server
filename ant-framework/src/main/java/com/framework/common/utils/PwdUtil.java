package com.framework.common.utils;

/**
 * Description: 用户密码工具
 * Auth: Frank
 * Date: 2017-10-26
 * Time: 下午 3:44
 */
public class PwdUtil {

    /**
     * 返回用户加密后的密码
     * <p>
     *    加密后的密码生成规则：
     *     1.先对密码进行加盐操作
     *     2.对加盐后的密码进行Base64加密
     * </p>
     * @param pwd 用户密码
     * @return 加密后的密码
     * @throws Exception
     */
    public static String getEncryptePwd(String pwd) throws Exception {
        String pwdSalt = SaltUtil.addSalt(pwd);
        return Base64Util.encryptBASE64(pwdSalt.getBytes());
    }

    /**
     * 校验用户密码是否与加密后的密码匹配
     * @param pwd 用户密码
     * @param pwdSaltBase64 加密后的密码
     * @return 是否匹配
     * @throws Exception
     */
    public static boolean verifyPwd(String pwd,String pwdSaltBase64) throws Exception {
        byte[] pwdSaltByte =  Base64Util.decryptBASE64(pwdSaltBase64);
        //Base64解密之后加盐密码
        String pwdSalt = new String(pwdSaltByte);
        return SaltUtil.verifyPwd(pwd,pwdSalt);
    }

}
