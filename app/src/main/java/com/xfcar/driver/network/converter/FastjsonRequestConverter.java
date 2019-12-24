package com.xfcar.driver.network.converter;

import com.alibaba.fastjson.JSON;
import com.xfcar.driver.utils.L;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * @author Linky
 */
public class FastjsonRequestConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {

        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);

        try {
            HashMap<String, Object> paramMap = sign(value);
            writer.write(JSON.toJSONString(paramMap));
            L.json(JSON.toJSONString(paramMap));
            writer.flush();
        } catch (Exception e) {
            throw new AssertionError(e); // Writing to Buffer does no I/O.
        }

        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }

    public static HashMap<String, Object> sign(Object obj) throws IllegalArgumentException,
            IllegalAccessException {
        HashMap<String, Object> hashMap = new HashMap<>();
        Class clazz = obj.getClass();
        List<Class> clazzs = new ArrayList<>();
        do {
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();

            if (clazz == null) {
                break;
            }
        } while (!clazz.equals(Object.class));

        for (Class iClazz : clazzs) {
            Field[] fields = iClazz.getDeclaredFields();
            for (Field field : fields) {
                Object objVal;
                field.setAccessible(true);
                objVal = field.get(obj);
                // 如果为null值 则不计入 md5摘要 ，因为 http传输层 不会把null值送出去 modified by janyo at 2015-5-7
                if (objVal != null)
                    hashMap.put(field.getName(), objVal);
            }
        }

//        String sign = SecurityUtils.signParams(hashMap);
//        hashMap.put("sign", sign);
        return hashMap;
    }
}
