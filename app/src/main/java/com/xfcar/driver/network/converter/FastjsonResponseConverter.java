package com.xfcar.driver.network.converter;

import com.alibaba.fastjson.JSON;
import com.xfcar.driver.utils.L;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * @author Linky
 */
public class FastjsonResponseConverter<T> implements Converter<ResponseBody, T> {

    private Type type;

    public FastjsonResponseConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        L.json(tempStr);
        bufferedSource.close();
        return JSON.parseObject(tempStr, type);
    }
}
