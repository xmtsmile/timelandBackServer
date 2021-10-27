package com.study.note.controller;

import com.study.note.entity.User;
import com.study.note.mapper.UserMapper;
import com.study.note.service.UserService;
import com.study.note.utils.token.TokenModel;
import com.study.note.utils.token.TokenHelper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.net.www.protocol.https.Handler;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/WxLogin")
public class WxLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenHelper tokenHelper;

    @PostMapping("/Wx")
    public Map<String, Object> wxlogin(@RequestBody String code){
        JSONObject jsonObject = JSONObject.fromObject(code);
        String jscode = jsonObject.getString("code");
        String token_url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx82394202869c2a47&secret=408d420003f2b6f5abd359d9070a4bc8&js_code=" +
                jscode + "&grant_type=authorization_code";
        JSONObject access_token = httpsRequestToJsonObject(token_url, "GET", null);
        String openid = access_token.getString("openid");
        String session_key = access_token.getString("session_key");

        TokenModel model = tokenHelper.create(openid);

        Map<String, Object> map = new HashMap<>();
        map.put("openid", openid);
        map.put("session_key", session_key);
        map.put("token", model.getToken());

        User user = userMapper.findById(openid);

        if(user==null){
            user = new User();
            user.setId(openid);

            userService.wxlogin(user);
        }
        else{
            System.out.println("用户已存在");
        }

        return map;
    }

    public static JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.err.println("请求连接超时"+ce);
        } catch (Exception e) {
            System.err.println("https请求异常：" + e.getMessage());
        }
        return jsonObject;
    }

    private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output)
            throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, MalformedURLException,
            IOException, ProtocolException, UnsupportedEncodingException {
        URL url = new URL(null, requestUrl, new Handler());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(requestMethod);
        if (null != output) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(output.getBytes("UTF-8"));
            outputStream.close();
        }
        // 从输入流读取返回内容
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        connection.disconnect();
        return buffer;
    }
}
