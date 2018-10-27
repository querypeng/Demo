package store.pengfeng.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author pengfeng
 *
 * * 类转化器
 * 借助FastJson从一个类转化为另一个类
 * 主要是用来转化同一个模型的Model和DO
 * 他们具有基本相同的属性名称
 */
public class ClazzConverter {
    /**
     * 具有相同属性名称的对象转化
     *
     * @param srcClazz
     * @param dstClazz
     * @return
     */
    public static <T1, T2> T1 converterClass(final T2 srcClazz, Class<T1> dstClazz) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(srcClazz);
        if (jsonObject == null) {
            return null;
        }
        return JSONObject.toJavaObject(jsonObject, dstClazz);
    }

    /**
     * 集合转化
     *
     * @param srcClazzCollection
     * @param dstClazz
     * @return
     */
    public static <T1, T2> Collection<T1> converterClass(final Collection<T2> srcClazzCollection, Class<T1> dstClazz) {
        JSONArray jsonArray = (JSONArray) JSONObject.toJSON(srcClazzCollection);
        if (jsonArray == null) {
            return null;
        }
        return JSONArray.parseArray(jsonArray.toJSONString(), dstClazz);
    }

    /**
     * 数组转化
     *
     * @param srcClazzArray
     * @param dstClazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T1, T2> T1[] converterClass(final T2[] srcClazzArray, Class<T1> dstClazz) {
        JSONArray jsonArray = (JSONArray) JSONObject.toJSON(srcClazzArray);
        if (jsonArray == null) {
            return null;
        }
        List<T1> result = JSONArray.parseArray(jsonArray.toJSONString(), dstClazz);
        if (result == null) {
            return null;
        }

        return (T1[]) result.toArray();
    }


    /**
     * 具有相同属性名称的对象转化
     *
     * @param srcClazz
     * @param dstClazz
     * @param filterNames 属性过滤
     * @return
     */
    public static <T1, T2> T1 converterClass(final T2 srcClazz, Class<T1> dstClazz, String[] filterNames) {
        //过滤属性
        if (filterNames != null && filterNames.length > 0) {
            SerializeFilter[] filters = new SerializeFilter[1];
            filters[0] = new ConverterPropertyFilter(filterNames);

            String result = JSONObject.toJSONString(srcClazz, filters, new SerializerFeature[0]);

            return JSONObject.toJavaObject(JSONObject.parseObject(result), dstClazz);
        } else {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(srcClazz);
            if (jsonObject == null) {
                return null;
            }
            return JSONObject.toJavaObject(jsonObject, dstClazz);
        }
    }

    public static <T1, T2> T1 converterClassWithDateFormat(final T2 srcClazz, Class<T1> dstClazz, String dateFormat) {
        String jsonString = JSON.toJSONStringWithDateFormat(srcClazz, dateFormat);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (jsonObject == null) {
            return null;
        }
        return JSONObject.toJavaObject(jsonObject, dstClazz);
    }

}

class ConverterPropertyFilter implements PropertyFilter {
    private List<String> filterNameList = new ArrayList<String>();

    public ConverterPropertyFilter(String[] filterNames) {
        if (filterNames != null) {
            for (String filterName : filterNames) {
                if (filterName != null && filterName.length() > 0) {
                    filterNameList.add(filterName);
                }
            }
        }
    }
    @Override
    public boolean apply(Object source, String name, Object value) {
        return !filterNameList.contains(name);
    }

}
