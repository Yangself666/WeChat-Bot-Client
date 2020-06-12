package cn.yangself.wechatBotClient.utils;

import java.util.UUID;

/**
 * 获取随机字符串
 */
public class UUIDRandom {
    private static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
