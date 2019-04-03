package com.framework.common.utils.token;

/**
 * <p>
 * 载荷就是存放有效信息的地方。这个名字像是特指飞机上承载的货品，这些有效信息包含三个部分
 * 1，标准中注册的声明
 * 2，公共的声明
 * 3，私有的声明
 * 标准中注册的声明 (建议但不强制使用) ：
 * iss: jwt签发者
 * sub: jwt所面向的用户
 * aud: 接收jwt的一方
 * exp: jwt的过期时间，这个过期时间必须要大于签发时间
 * nbf: 定义在什么时间之前，该jwt都是不可用的.
 * iat: jwt的签发时间
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放
 * </p>
 * Auth: Frank
 * Date: 2017-11-02
 * Time: 下午 5:10
 */
public class TokenPlayload<T> {
    private String iss;// jwt签发者
    private String sub;// jwt所面向的用户
    private String aud;// 接收jwt的一方
    private String exp;// jwt的过期时间，这个过期时间必须要大于签发时间
    private String nbf;//定义在什么时间之前，该jwt都是不可用的.
    private String iat;//jwt的签发时间
    private String jti;//jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
    private T expData;//拓展数据

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getNbf() {
        return nbf;
    }

    public void setNbf(String nbf) {
        this.nbf = nbf;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public T getExpData() {
        return expData;
    }

    public void setExpData(T expData) {
        this.expData = expData;
    }
}
