package com.summer.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.summer.controller.BaseController;
import com.summer.entity.User;
import com.summer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 前台天猫-登陆页
 */
@Controller
public class ForeLoginController extends BaseController {
    @Autowired
    private UserService userService;

    //转到前台天猫-登录页
    @RequestMapping(value = "login", method = {RequestMethod.POST, RequestMethod.GET})
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("转到前台天猫-登录页");
        return "fore/loginPage";
    }

    //登陆验证-ajax
    /*@responseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，
    写入到response对象的body区，通常用来返回JSON数据或者是XML
　　数据，需要注意的呢，在使用此注解之后不会再走试图处理器，
而是直接将数据写入到输入流中，他的效果等同于通过response对象输出指定格式的数据。*/
    @ResponseBody
    /*produce指定返回json数据 字符编码为utf8
    * Spring中解析字符串的转换器默认编码是ISO-8859-1 会导致中文乱码 */
    @RequestMapping(value = "login/doLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String checkLogin(HttpSession session, @RequestParam String username, @RequestParam String password) {
        logger.info("用户验证登录");
        User user = userService.login(username, password);

        JSONObject jsonObject = new JSONObject();
        if (user == null) {
            logger.info("登录验证失败");
            jsonObject.put("success", false); //键值对储存
        } else {
            logger.info("登录验证成功,用户ID传入会话");
            session.setAttribute("userId", user.getUser_id());
            jsonObject.put("success", true);
        }
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "login/checkCode", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String checkCode(HttpSession session, @RequestParam String code)
    {
        logger.info("人工验证");
        JSONObject jsonObject = new JSONObject();
        String validationCode=session.getAttribute("validationCode").toString();
        if(code.equals(validationCode))
        {
            logger.info("人工验证成功");
            jsonObject.put("success", true);
        }
        else
        {
            logger.info("登录验证失败");
            jsonObject.put("success", false); //键值对储存
        }
        return jsonObject.toJSONString();
    }


    //退出当前账号
    @RequestMapping(value = "login/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        Object o = session.getAttribute("userId");
        if (o != null) {
            session.removeAttribute("userId");
            session.invalidate();
            logger.info("登录信息已清除，返回用户登录页");
        }
        return "redirect:/login";
    }
}
