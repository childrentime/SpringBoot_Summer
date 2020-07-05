package com.summer.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.summer.controller.BaseController;
import com.summer.entity.Category;
import com.summer.entity.Product;
import com.summer.entity.User;
import com.summer.service.UserService;
import com.summer.util.OrderUtil;
import com.summer.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 前台天猫-主页
 */
@Controller
public class ForeHomeController extends BaseController {
    @Autowired
    private UserService userService;


    //转到前台天猫-主页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    /*服务器可以为每个用户浏览器创建一个会话对象（session对象），一个浏览器只能产生一个session，
    当新建一个窗口访问服务器时，还是原来的那个session。session中默认保存的是当前用户的信息。
    因此，在需要保存其他用户数据时，我们可以自己给session添加属性。
    session（会话）可以看为是一种标识，通过带session的请求，可以让服务器知道是谁在请求数据。
     */
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        logger.info("转到前台主页");
        return "fore/homePage";
    }
}

