package com.framework.common.utils.token;

import com.alibaba.fastjson.JSON;
import com.framework.common.utils.AesUtil;
import com.framework.common.utils.Base64Util;
import com.framework.common.utils.SaltUtil;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class TokenUtils {
    public static final  String TOKEN_AES_KEY = "xiangli8Token";
    public static final  String REFREH_TOKEN_AES_KEY = "xiangli8RefreshToken";
    public static final  String JWT_TYP = "JWT";
    public static final  String JWT_ALG = "AES";
    public static final  String JWT_EXP = "30";
    public static final  String JWT_ISS = "xiangli8";

    /**
     * 获得token
     * @param data 自定义数据
     * @param <T> 自定义数据
     * @return
     * @throws Exception
     */
    public static <T> String getToken(T data) throws Exception {
        TokenPlayload<T> userTokenPlayload = new TokenPlayload<>();
        userTokenPlayload.setExpData(data);
        String jwt = createJWT(userTokenPlayload);
        return jwt;
    }

    /**
     * 生成jwt的header部分内容
     * @return
     * @throws Exception
     */
    private static String tokenHeaderBase64()  {
        TokenHeader tokenHeader = new TokenHeader();
        tokenHeader.setTyp(JWT_TYP);
        tokenHeader.setAlg(JWT_ALG);

        String headerJson = JSON.toJSONString(tokenHeader);

        String headerBase64 = Base64Util.encryptBASE64(headerJson.getBytes());

        return headerBase64;
    }

    /**
     * 生成jwt的payload部分内容
     * @param tokenPlayload
     * @param <T>自定义的数据块
     * @return
     * @throws Exception
     */
    private static <T> String tokenPayloadBase64(TokenPlayload<T> tokenPlayload)  {
        tokenPlayload.setIss(JWT_ISS);
        tokenPlayload.setExp(JWT_EXP);

        tokenPlayload.setIat(String.valueOf(System.currentTimeMillis()));

        String headerJson =JSON.toJSONString(tokenPlayload);

        String headerBase64 = Base64Util.encryptBASE64(headerJson.getBytes());

        return headerBase64;
    }

    /**
     * 生成JWT
     * @return
     */
    public static <T> String createJWT(TokenPlayload<T> tokenPlayload) throws Exception {
        StringBuilder jwtSb = new StringBuilder();
        StringBuilder headerPlayloadSb = new StringBuilder();

        String tokenHeaderBase64 = tokenHeaderBase64();
        String tokenPayloadBase64 = tokenPayloadBase64(tokenPlayload);

        jwtSb.append(tokenHeaderBase64);
        jwtSb.append(".");
        jwtSb.append(tokenPayloadBase64);
        jwtSb.append(".");

        headerPlayloadSb.append(tokenHeaderBase64);
        headerPlayloadSb.append(tokenPayloadBase64);

        String headerPlayloadSalt = SaltUtil.addSalt(headerPlayloadSb.toString());

        String key = AesUtil.initKey(TOKEN_AES_KEY+tokenPlayload.getIat());

        String  signature = Base64Util.encryptBASE64(AesUtil.encrypt(headerPlayloadSalt.getBytes(),key));

        jwtSb.append(signature);

        return Base64Util.encryptBASE64(jwtSb.toString().getBytes());
    }

    /**
     * 校验token是否是服务器生成的，以防token被修改
     * @param jwtBase64
     * @return
     * @throws Exception
     */
    public static <T> boolean verifyJWT(String jwtBase64) throws Exception {
        String jwt = new String (Base64Util.decryptBASE64(jwtBase64));

        if(!jwt.contains(".")){
            return false;
        }

        String[] jwts = jwt.split("\\.");
        if(jwts.length<3){
            return false;
        }

        TokenPlayload tTokenPlayload =  JSON.parseObject(new String(Base64Util.decryptBASE64(jwts[1])),TokenPlayload.class);
        String key = AesUtil.initKey(TOKEN_AES_KEY+tTokenPlayload.getIat());

        //解析出header跟playload
        StringBuilder headerPlayloadSb = new StringBuilder();
        headerPlayloadSb.append(jwts[0]);
        headerPlayloadSb.append(jwts[1]);

        //解析signature
        String  headerPlayloadSalt = new String (AesUtil.decrypt(Base64Util.decryptBASE64(jwts[2]),key));

        return SaltUtil.verifyPwd(headerPlayloadSb.toString(),headerPlayloadSalt);
    }

    /**
     * 生成refreshToken
     * @return
     */
    public static String createRefreshToken(String jwt) throws Exception {
        StringBuilder seedSb = new StringBuilder();
        seedSb.append(TOKEN_AES_KEY).append(jwt).append(UUID.randomUUID());
        String key = AesUtil.initKey(seedSb.toString());
        String  refreshToken = Base64Util.encryptBASE64(AesUtil.encrypt(jwt.getBytes(),key));
        return refreshToken;
    }

}
