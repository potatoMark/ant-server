package com.framework.common.utils;

import com.framework.common.entity.DynamicTabContentVO;
import com.framework.common.entity.DynamicTabVO;
import com.framework.modules.tool.pojo.ToolDynamicTab;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private String changeName(String str){

        String[] strs = str.split("_");

        int loop = 0;
        if (strs.length == 1) {
            return strs[0].substring(0,1).toUpperCase().concat(strs[0].substring(1).toLowerCase());
        }

        String content = "";
        for ( ; loop<strs.length; loop++ ) {

            content = content.concat(strs[loop].substring(0,1).toUpperCase().concat(strs[loop].substring(1).toLowerCase()));

        }

        return content;
    }

    public static void dynamicSetValue(ToolDynamicTab toolDynamicTab , Map<String,Object> map){
        if (map != null) {
            toolDynamicTab.getToolDynamicTabComponents().forEach(item->{
                map.forEach((k,v)->{
                    if (item.getContentNumber().equals(k)) {
                        item.setContentValue(v);
                    }
                });
            });

            map.forEach((k,v)->{
                if ("id".equals(k)) {
                    Integer i = (Integer)v;
                    toolDynamicTab.setTabId(i.longValue());
                }
            });
        }
    }

    public static List<DynamicTabVO> dynamicPojoToVo(List<ToolDynamicTab> toolDynamicTabs){

        List<DynamicTabVO> dynamicTabVOS = new ArrayList<>();
        toolDynamicTabs.forEach(item->{
            DynamicTabVO dynamicTabVO = new DynamicTabVO();
            dynamicTabVO.setLabel(item.getTabTableName());
            dynamicTabVO.setName(item.getTabTableNumber());
            dynamicTabVO.setTabId(item.getTabId());

            List<DynamicTabContentVO> dynamicTabContentVOS = new ArrayList<>();

            item.getToolDynamicTabComponents().forEach(node->{
                DynamicTabContentVO dynamicTabContentVO = new DynamicTabContentVO();
                dynamicTabContentVO.setContentNumber(node.getContentNumber());
                dynamicTabContentVO.setContentName(node.getContentName());
                dynamicTabContentVO.setContentRequired(node.getContentRequired());
                dynamicTabContentVO.setContentType(node.getContentType());
                String[] strs = StringUtils.isNotBlank(node.getContentItem())?node.getContentItem().split(","):null;
                dynamicTabContentVO.setContentItems(strs);
                dynamicTabContentVO.setContentValue(node.getContentValue());

                dynamicTabContentVOS.add(dynamicTabContentVO);
            });

            dynamicTabVO.setContents(dynamicTabContentVOS);

            dynamicTabVOS.add(dynamicTabVO);
        });

        return dynamicTabVOS;
    }
    public static String generateDeleteSql(ToolDynamicTab dynamicTabVO,String fkName,Long fkId){
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM ");
        sql.append(dynamicTabVO.getTabTableNumber());
        sql.append(" WHERE ");
        sql.append(fkName.concat(" = "));
        sql.append(fkId);

        return sql.toString();
    }
    public static String generateDynamicSql(DynamicTabVO dynamicTabVO,String fkName,Long fkId)throws Exception{

        if (dynamicTabVO.getTabId() == null) {
            //insert
            if (isNotBlank(dynamicTabVO.getContents())){
                return generateInsertSql(dynamicTabVO.getContents(), dynamicTabVO.getName(), fkName, fkId);
            }
        } else {
            //update
            return generateUpdateSql(dynamicTabVO.getContents(), dynamicTabVO.getName(), fkName, fkId, dynamicTabVO.getTabId());
        }


        return "";
    }

    public static boolean isNotBlank(List<DynamicTabContentVO> contents) {

        boolean flg = false;
        for (DynamicTabContentVO item : contents) {
            if (item.getContentValue() != null && !"".equals(item.getContentValue().toString().trim())) {
                flg = true;
                break;
            }
        }

        return flg;
    }

    public static String generateInsertSql(List<DynamicTabContentVO> contents,String tableName,String fkName,Long fkId)throws Exception {

        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO ");
        sql.append(tableName);
        sql.append(" (".concat(fkName));
        for (DynamicTabContentVO content : contents) {
            if (content.getContentValue() != null)sql.append(" ,".concat(content.getContentNumber()));
        }
        sql.append(" ) VALUES (");
        sql.append(fkId.longValue());
        for (DynamicTabContentVO content : contents) {
            switch (content.getContentType()){
//                case "Date":
//                    if (content.getContentValue()!= null) sql.append(", ".concat(translationData(content.getContentValue())));
//                    break;
//                case "DateTime":
//                    if (content.getContentValue()!= null) sql.append(", ".concat(translationData(content.getContentValue())));
//                    break;
                default:
                    if (content.getContentValue()!= null) sql.append(", ".concat(translationData(content.getContentValue())));
                    break;
            }
        }
        sql.append(" )");

        return sql.toString();
    }

    private static String translationData(Object obj) throws Exception{
//        String regYmd = "^\\d{4}-\\d{2}-\\d{2}$";
//        String regYmdHms = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        if (obj == null || StringUtils.isBlank(obj.toString())) {
            return "null";
        } else {
            return "'".concat(obj.toString()).concat("'");
        }

    }

    public static String generateUpdateSql(List<DynamicTabContentVO> contents,String tableName,String fkName,Long fkId,Long pkId)throws Exception {

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE ");
        sql.append(tableName);
        sql.append(" SET ");
        sql.append(fkName.concat(" = "));
        sql.append(fkId.longValue());
        for (DynamicTabContentVO content : contents) {
            switch (content.getContentType()){
//                case "Date":
//                    sql.append(","+content.getContentNumber()+" = ".concat(translationData(content.getContentValue())));
//                    break;
//                case "DateTime":
//                    sql.append(","+content.getContentNumber()+" = ".concat(translationData(content.getContentValue())));
//                    break;
                default:
                    sql.append(","+content.getContentNumber()+" = ".concat(translationData(content.getContentValue())));
                    break;
            }
        }
        sql.append(" WHERE ");
        sql.append(" id = ");
        sql.append(pkId.longValue());
        return sql.toString();
    }
}
