$(function () {

    //二维码动画
    $("#qrcodeA").hover(
        function () {
            $(this).stop().animate({left: "13px"}, 450, function () {
                $("#qrcodeB").stop().animate({opacity: 1}, 300)
            });
        }
        , function () {
            $("#qrcodeB").css("opacity", "0");
            $(this).stop().animate({left: "80px"}, 450);
        });
    //登录方式切换
    $("#loginSwitch").click(function () {
        var messageSpan = $(".loginMessageMain").children("span");
        if ($(".pwdLogin").css("display") === "block") {
            $(".pwdLogin").css("display", "none");
            $(".qrcodeLogin").css("display", "block");
            messageSpan.text("密码登录在这里");
            $(this).removeClass("loginSwitch").addClass("loginSwitch_two");
        } else {
            $(".pwdLogin").css("display", "block");
            $(".qrcodeLogin").css("display", "none");
            messageSpan.text("扫码登录更安全");
            $(this).removeClass("loginSwitch_two").addClass("loginSwitch");
        }
    });
    $("#pwdLogin").click(function () {
        var messageSpan = $(".loginMessageMain").children("span");
        $(".pwdLogin").css("display", "block");
        $(".qrcodeLogin").css("display", "none");
        messageSpan.text("扫码登录更安全");
        $("#loginSwitch").removeClass("loginSwitch_two").addClass("loginSwitch");
    });
    //登录验证
    $(".loginForm").submit(function () {
        var yn = true;
        $(this).find(":password").each(function () {
            if ($.trim($(this).val()) === "") {
                styleUtil.errorShow($("#error_message_p"), "请输入密码！");
                yn = false;
                return yn;
            }
        });
        $(this).find("#name").each(function () {
            if ($.trim($(this).val()) === "") {
                styleUtil.errorShow($("#error_message_p"), "请输入用户名！");
                yn = false;
                return yn;
            }
        });
       $(this).find("#checkCode").each(function () {
           if ($.trim($(this).val()) === "") {
               styleUtil.errorShow($("#error_message_p"), "请输入验证码！");
               yn = false;
               return yn;
           } else {
               $.ajax({
                   type: "POST",
                   /*调用login控制器方法验证*/
                   url: "/tmall/login/checkCode",
                   data: {"code": $.trim($(this).val())},
                   dataType: "json",
                   success: function (data) {
                       if (data.success) {
                           styleUtil.errorShow($("#error_message_p"), "验证码输入正确！");
                           if (yn) {
                               $.ajax({
                                   type: "POST",
                                   /*调用login控制器方法验证*/
                                   url: "/tmall/login/doLogin",
                                   data: {"username": $.trim($("#name").val()), "password": $.trim($("#password").val())},
                                   dataType: "json",
                                   success: function (data) {
                                       $(".loginButton").val("登 录");
                                       if (data.success) {
                                           location.href = "/tmall";
                                       } else {
                                           styleUtil.errorShow($("#error_message_p"), "用户名和密码错误！");
                                       }
                                   },
                                   error: function (data) {
                                       $(".loginButton").val("登 录");
                                       styleUtil.errorShow($("#error_message_p"), "服务器异常，请刷新页面再试！");
                                   },
                                   beforeSend: function () {
                                       $(".loginButton").val("正在登录...");
                                   }
                               });
                           }
                       } else {
                           styleUtil.errorShow($("#error_message_p"), "验证码输入错误！");
                       }
                   }
               });
           }
       });




        return false;
    });
    $(".loginForm :text,.loginForm :password").focus(function () {
        styleUtil.errorHide($("#error_message_p"));
    });
});