package com.szlaun.launtech.system.sap.service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;
import com.szlaun.launtech.system.sap.dto.CustomDestinationDataProvider;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @Description
 * @Author lizhiming
 * @Date 2020/9/23 16:35
 * @Version V1.0
 **/
@Service("SAPConnectionPool")
public class SAPConnectionPool {
    private static final String SAP_CONN="SAP_CONN";

    public static JCoDestination getSAPDestination(){
        try {
            JCoDestination dest = JCoDestinationManager.getDestination(SAP_CONN);
            return dest;
        } catch (JCoException ex) {
            //System.out.println(ex);
            //System.out.println("连接失败,重新连接!");
            //重新连接
            return RegetJocodestination();
        }
    }

    /*****
     * 函数功能描述：重新获取JCODestination
     * @return
     ****
     */
    public static JCoDestination RegetJocodestination(){
        try{
            Properties properFile = new Properties();
            ClassLoader cl=Thread.currentThread().getContextClassLoader();
            String filePath=cl.getResource("").toString()+"SAPConnectionPool.properties";
            filePath=filePath.replace("file:", "");
            filePath=filePath.replace("%20", " ");//替换文件名包含空格的
            FileInputStream inputFile = new FileInputStream(filePath);
            properFile.load(inputFile);
            inputFile.close();

            Properties connectProperties = new Properties();
            connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, properFile.getProperty("jco.client.ashost"));
            connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,  properFile.getProperty("jco.client.sysnr"));
            connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, properFile.getProperty("jco.client.client"));
            connectProperties.setProperty(DestinationDataProvider.JCO_USER,   properFile.getProperty("jco.client.user"));
            connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, properFile.getProperty("jco.client.passwd"));
            connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, properFile.getProperty("jco.destination.pool_capacity"));
            connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT,   properFile.getProperty("jco.destination.peak_limit"));
            connectProperties.setProperty(DestinationDataProvider.JCO_LANG,   properFile.getProperty("jco.client.lang"));

            CustomDestinationDataProvider provider = new CustomDestinationDataProvider();
            provider.addDestinationProperties(SAP_CONN, connectProperties);
            Environment.registerDestinationDataProvider(provider);
            try {
                JCoDestination dest = JCoDestinationManager.getDestination(SAP_CONN);
                return dest;
            } catch (JCoException ex) {
                System.out.println(ex);
                System.out.println("重新连接失败");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
