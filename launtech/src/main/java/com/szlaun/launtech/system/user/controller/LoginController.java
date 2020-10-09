package com.szlaun.launtech.system.user.controller;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.szlaun.launtech.anno.Authority;
import com.szlaun.launtech.system.sap.service.SAPConnectionPool;
import com.szlaun.launtech.system.user.dto.User;
import com.szlaun.launtech.system.user.service.UserService;
import com.szlaun.launtech.utils.Constant;
import com.szlaun.launtech.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description 登录/用户
 * @Author lizhiming
 * @Date 2020/9/22 13:38
 * @Version V1.0
 **/
@Controller
public class LoginController {

    private static int count = 0;
    @Autowired
    private UserService userService;

    @Authority("aaaa")
    @ResponseBody
    @GetMapping("/getIndex2")
    public String getIndex2() {
        DataOutputStream dataOut = null;
        BufferedReader in = null;
        try {
            //API endpoint for API sandbox
            String url = "https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_OPLACCTGDOCITEMCUBE_SRV/A_OperationalAcctgDocItemCube(CompanyCode='{CompanyCode}',FiscalYear='{FiscalYear}',AccountingDocument='{AccountingDocument}',AccountingDocumentItem='{AccountingDocumentItem}')";
            //API endpoint with optional query parameters
            //String url = "https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_OPLACCTGDOCITEMCUBE_SRV/A_OperationalAcctgDocItemCube(CompanyCode='{CompanyCode}',FiscalYear='{FiscalYear}',AccountingDocument='{AccountingDocument}',AccountingDocumentItem='{AccountingDocumentItem}')?$select=array";

            //Available API Endpoints
            //https://{host}:{port}/sap/opu/odata/sap/API_OPLACCTGDOCITEMCUBE_SRV

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            //setting request method
            connection.setRequestMethod("GET");

            //adding headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            //API Key for API Sandbox
            connection.setRequestProperty("APIKey", "yMujBz4ZdG338PRtybY12nWuVbbzmffz");

            //Available Security Schemes for productive API Endpoints
            //Basic Authentication

            //Basic Auth : provide username:password in Base64 encoded in Authorization header
            //connection.setRequestProperty("Authorization","Basic <Base64 encoded value>");

            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            //printing response
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            //do something with exception
            e.printStackTrace();
        } finally {
            try {
                if (dataOut != null) {
                    dataOut.close();
                }
                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                //do something with exception
                e.printStackTrace();
            }
            int i = 1 / 0;
            return "index";
        }
    }

    @Authority("aaaa")
    @ResponseBody
    @GetMapping("/getIndex3")
    public String getIndex3() {
        try {
            JCoDestination destination = SAPConnectionPool.getSAPDestination();//获取连接
            //返回一个JCoFunction初始参数的传递函数名。获取接口方法
            JCoFunction function = destination.getRepository().getFunction("Function_NAME");

            //1.单独的参数，不在表结构下
            function.getImportParameterList().setValue("NAME1", "VALUE1");// 参数

            //2.JCoStructure 一般为HEADER参数
            JCoStructure Structure = function.getImportParameterList().getStructure("_HEADER");
            Structure.setValue("NAME1", "VALUE1");//参数
            Structure.setValue("NAME2", "VALUE2");//参数

            //3.JCoTable 主体参数，可为多个主体参数。。。
            JCoTable headerImportParam = function.getTableParameterList().getTable("_TABLE");//返回的值i个字段作为一个表
            //如果为参数集合，在外层加for循环即可
            headerImportParam.appendRow();//附加表的最后一个新行,行指针,它指向新添加的行。
            headerImportParam.setValue("NAME1", "VALUE1");//参数
            headerImportParam.setValue("NAME2", "VALUE2");//参数

            //执行接口
            function.execute(destination);

            //接口返回结果
            JCoTable returnStructure = function.getTableParameterList().getTable("TD_RETURN");
            for (int i = 0; i < returnStructure.getNumRows(); i++) {
                returnStructure.setRow(i);
                System.out.println(returnStructure.getString("Param1"));
                System.out.println(returnStructure.getString("Param2"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 登录验证
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResultMsg login(HttpServletRequest request) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        System.out.println(count++);
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            return ResultMsg.getError("登录失败,账户或密码不能为空！");
        } else {
            User user = userService.verifyAccount(account, password);
            if (user != null) {
                request.getSession().setAttribute(Constant.SESSION_ACCOUNT_FLAGE, user);
                return ResultMsg.getSuccess("登录成功！");
            } else {
                return ResultMsg.getError("登录失败,账户或密码错误！");
            }
        }
    }

}
