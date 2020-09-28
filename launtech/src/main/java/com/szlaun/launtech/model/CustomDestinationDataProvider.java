package com.szlaun.launtech.model;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description
 * @Author lizhiming
 * @Date 2020/9/23 16:37
 * @Version V1.0
 **/
public class CustomDestinationDataProvider implements DestinationDataProvider {
    private Map providers = new HashMap();
    @Override
    public Properties getDestinationProperties(String destName) {
        if (destName == null)
            throw new NullPointerException("请指定目的名称");
        if (providers.size() == 0)
            throw new IllegalStateException("请加入一个目的连接参数属性给提供者");
        return (Properties)providers.get(destName);
    }

    // 没有实现事件处理
    @Override
    public boolean supportsEvents(){
        return false;
    }

    @Override
    public void setDestinationDataEventListener(DestinationDataEventListener listener) {
        throw new UnsupportedOperationException();
    }

    public void addDestinationProperties(String destName, Properties provider) {
        providers.put(destName, provider);
    }
}
