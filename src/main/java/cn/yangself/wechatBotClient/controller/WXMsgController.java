package cn.yangself.wechatBotClient.controller;

import cn.yangself.wechatBotClient.service.WXServerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wxbot")
public class WXMsgController {
    @Autowired
    private WXServerListener wxServerListener;


    @PostMapping("/textMsg")
    @ResponseBody
    public Map<String,Object> sendTextMsg(@RequestBody Map<String,Object> sendMap){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            String wxid = sendMap.get("wxid").toString();
            String msg = sendMap.get("msg").toString();
            wxServerListener.sendTextMsg(wxid,msg);
            resultMap.put("code", 200);
            resultMap.put("msg", "发送成功！");
        }catch(Exception e){
            resultMap.put("code", 500);
            resultMap.put("msg", "发送失败！服务器发生错误！");
            resultMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
        }
        return resultMap;
    }

    //暂不可用
    //@PostMapping("/atMsg")
    //@ResponseBody
    //public Map<String,Object> sendAtMsg(@RequestBody Map<String,Object> sendMap){
    //    Map<String,Object> resultMap = new HashMap<>();
    //    try{
    //        //String wxid = sendMap.get("wxid").toString();
    //        //String text = sendMap.get("text").toString();
    //        //String text = "null";
    //        //String roomId = sendMap.get("roomId").toString();
    //        wxServerListener.sendAtMsg();
    //        resultMap.put("code", 200);
    //        resultMap.put("msg", "发送成功！");
    //    }catch(Exception e){
    //        resultMap.put("code", 500);
    //        resultMap.put("msg", "发送失败！服务器发生错误！");
    //        resultMap.put("errorMsg", e.getMessage());
    //        e.printStackTrace();
    //    }
    //    return resultMap;
    //}

    @GetMapping("/contactList")
    @ResponseBody
    public Map<String,Object> getContact(){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            wxServerListener.getContactList();
            resultMap.put("code", 200);
            resultMap.put("msg", "发送成功！");
        }catch(Exception e){
            resultMap.put("code", 500);
            resultMap.put("msg", "发送失败！服务器发生错误！");
            resultMap.put("errorMsg", e.getMessage());
            e.printStackTrace();
        }
        return resultMap;
    }
}
