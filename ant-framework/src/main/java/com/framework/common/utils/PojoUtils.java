package com.framework.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PojoUtils {

    public static void changeDate(Object obj){

        try {
            Class clazz = obj.getClass();

            Method getId = clazz.getMethod("getId");

            Method setCreatedate = clazz.getMethod("setCreatedate", Timestamp.class);
            Method setUpdatedate = clazz.getMethod("setUpdatedate", Timestamp.class);

            Method setUpdateuser = clazz.getMethod("setUpdateuser", String.class);
            Method setCreateuser = clazz.getMethod("setCreateuser", String.class);

            Long id = (Long) getId.invoke(obj);


            if (id == null) {

                setCreatedate.invoke(obj, new Timestamp(new Date().getTime()));
                setCreateuser.invoke(obj,UserUtils.getCurrentLoginUser().getUsercode());
                setUpdatedate.invoke(obj, new Timestamp(new Date().getTime()));
                setUpdateuser.invoke(obj,UserUtils.getCurrentLoginUser().getUsercode());
            } else {

                setUpdatedate.invoke(obj, new Timestamp(new Date().getTime()));
                setUpdateuser.invoke(obj,UserUtils.getCurrentLoginUser().getUsercode());
            }

        }catch (Exception e) {
        }
    }
}
