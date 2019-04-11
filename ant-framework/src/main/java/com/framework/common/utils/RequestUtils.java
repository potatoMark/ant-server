package com.framework.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RequestUtils {

    private Map<String, Object> page;
    private List<Map<String, Object>> condition;
    private Map<String, Object> conditionMap;

    public Map<String, Object> getConditionMap() {
        conditionMap = new HashMap<String, Object>();

        for (Map<String, Object> map : condition) {
            conditionMap.put(String.valueOf(map.get("item")),map.get("value"));
        }
        return conditionMap;
    }
    public Map<String, Object> getNotNullConditionMap() {
        conditionMap = new HashMap<String, Object>();

        if(condition != null) {
            for (Map<String, Object> map : condition) {
                if (StringUtils.isNotEmpty(String.valueOf(map.get("value")))) {
                    conditionMap.put(String.valueOf(map.get("item")),map.get("value"));
                }
            }
        }
        return conditionMap;
    }
}
