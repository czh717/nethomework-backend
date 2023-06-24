package com.example.demo.mapper;

import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralInsertUpdateSqlProvider {

    /**
     * @param obj 插入对象, 用来获取字段和表名
     * @param ignoredCols 插入时需要忽略的列名 列名格式是Underscore，如created_at
     * @return sql
     *  插入单个参数（除了ignoredCols以外的参数）时，mapper写方法就行，实例见 UserMapper.insert
     *  插入时填入了ignoredCols参数（比如不要id、startTime等自增、默认值字段），实例见PipelineRecordMapper.insert
     */
    public static String insert(Object obj,String... ignoredCols){
        Map<String, String> map;
        try {
            map = getFieldsMap(obj, true, false);
            for (String col: ignoredCols){
                map.remove(camelCase2Underscore(col));
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return getInsertSQL(obj, map);
    }

    private static String getInsertSQL(Object obj, Map<String, String> map){
        return new SQL() {
            {
                INSERT_INTO(getTableName(obj));
                for (String col : map.keySet()) {
                    VALUES(col, map.get(col));
                }
            }
        }.toString();
    }

    /**
     * 更新obj所含的字段，但是不包括为null的字段
     */
    public static String updateById(Object obj) {
        return updateById(obj, false);
    }

    /**
     * 更新obj所含的字段，但是包括为null的字段
     */
    public static String updateWithNullById(Object obj) {
        return updateById(obj, true);
    }

    /**
     * 按传入的列名更新字段
     */
    public static String updateWithColsById(Object obj, String... cols) {
        return updateById(obj, true, cols);
    }

    private static String updateById(Object obj, boolean includeNullValueField, String... cols) {
        Map<String, String> map;
        try {
            map = getFieldsMap(obj, includeNullValueField, cols.length == 0);
            map.remove("id");
            //有可变长参数就保留可边长参数
            if (cols.length!=0){
                Set<String> colsSet = Arrays.stream(cols).collect(Collectors.toSet());
                map = map.entrySet().stream()
                        .filter(e -> colsSet.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return getUpdateSQL(obj,map);
    }

    private static String getUpdateSQL(Object obj, Map<String, String> map){
        return new SQL() {
            {
                UPDATE(getTableName(obj));
                for (String col : map.keySet()) {
                    SET(col + "=" + map.get(col));
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }


    /**
     *
     * @param obj 获取字段名的对象
     * @param includeNullValue 是否要获取为null的字段名
     * @param singleParam sql参数只有一个实体类的时候 设置为true，多个参数的时候设置false
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static Map<String, String> getFieldsMap(Object obj, boolean includeNullValue, boolean singleParam)
            throws IllegalArgumentException, IllegalAccessException {
        HashMap<String, String> result = new HashMap<>();
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields(); // getDeclaredFields
        for (Field f : fields) {
            String col = f.getName();
            String colName = col.substring(0, 1).toUpperCase() + col.substring(1);
            f.setAccessible(true);
            try {
                cls.getMethod("get" + colName);
                cls.getMethod("set" + colName, f.getType());
            } catch (NoSuchMethodException | SecurityException e) {
                continue;
            }
            /**
             * 单参数的时候
             * #{colname}可以直接取到实体的属性
             * 多参数的时候
             * #{param1.colname}来取实体的属性
             * 注：实体类一定要放第一个参数
             */
            String paramPrefix =  singleParam? "#{" : "#{param1.";
            if ((!"id".equals(col) && includeNullValue) || f.get(obj) != null) {
                result.put(camelCase2Underscore(col), paramPrefix + col + "}");
            }
        }
        return result;
    }

    public static String getTableName(Object obj) {
        return camelCase2Underscore(obj.getClass().getSimpleName().replaceAll("PO",""));
    }

    public static String camelCase2Underscore(String s) {
        StringBuffer buf = new StringBuffer();
        for (String w : s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
            buf.append("_").append(w.toLowerCase());
        }

        return "`"+ buf.substring(1) + "`";
    }

}
