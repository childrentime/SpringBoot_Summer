package com.summer.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * 基控制器
 */
public class BaseController {
    //log4j
    //LogManager,获取Logger实例或者操作当前的LoggerRepository。
    //LogManager类的初始化过程在加载到内存前已经完成。
    //ROOT_LOGGER_NAME：Case insensitive String constant used to retrieve the name of the root logger.
    protected Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    //检查管理员权限
    protected Object checkAdmin(HttpSession session){
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return null;
        }
        logger.info("权限验证成功，管理员ID：{}",o);
        return o;
    }

    //检查用户是否登录
    protected Object checkUser(HttpSession session){
        Object o = session.getAttribute("userId");
        if(o==null){
            logger.info("用户未登录");
            return null;
        }
        logger.info("用户已登录，用户ID：{}", o);
        return o;
    }
}
