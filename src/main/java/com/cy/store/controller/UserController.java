package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
       /* JsonResult<Void> result = new JsonResult<>();

        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");

        } catch (InsertException e) {
            result.setState(5000);
            result.setMessage("注册产生未知的异常");
        }
        return  result;*/
        userService.reg(user);
        return  new JsonResult<>(OK);




    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession httpSession) {
        User data= userService.login(username, password);
        //向session对象中完成数据的绑定（session全局的）
        httpSession.setAttribute("uid",data.getUid());
        httpSession.setAttribute("username",data.getUsername());

        //获取session中绑定的数据
        System.out.println(getUidFromSession(httpSession));
        System.out.println(getUsernameFromSession(httpSession));

        return  new JsonResult<User>(OK,data);

    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session) {
        Integer uidFromSession = getUidFromSession(session);
        String usernameFromSession = getUsernameFromSession(session);
        userService.changePassword(uidFromSession,usernameFromSession,oldPassword,newPassword);
        return new  JsonResult<Void>(OK);

    }

    @RequestMapping("get_by_uid")
    public  JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getUidFromSession(session));
        return  new JsonResult<User>(OK,data);


    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return  new  JsonResult<>(OK);


    }




    /**
     * MultipartFile接口是SpringMVC提供了一个接口，这个接口为为我们包装了获取文件类型的数据
     * （任何类型的文件），springboot整合了SpringMVC，只需要在处理请求的方法的参数列表上
     * 申明一个参数类型为MultipartFile的参数，然后SpringBoot将自动传递服务的文件数据赋值给这个参数
     *
     * @param httpSession
     * @param file
     * @return
     */
    //设置上传文件的最大值
    public static  final int AVATAR_MAX_SIZE = 10*1024*1024;

    /*限制上传文件的类型*/
    public static final List<String> AVATAR_TYPE =  new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");


    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file) {

        if (file.isEmpty()) {
            throw  new FileEmptyException("文件为空");
        }
        if ( file.getSize() > AVATAR_MAX_SIZE) {
            throw  new FileSizeException("文件超出限制");
        }
        String contentType = file.getContentType();
        if (!AVATAR_TYPE.contains(contentType)) {
            throw  new FileTypeException("文件类型不支持");

        }
        String parent = session.getServletContext().getRealPath("upload");

        //File对象指向这个路径，File是否存在
        File dir = new File(parent);
        if (!dir.exists()) {  //检测目录是否存在
            dir.mkdirs(); // 创建当前的目录
        }

        //获取到这个文件的名称，UUID工具来生成一个新的字符串作为文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("OriginalFilename"+originalFilename);

        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);

        String fileName = UUID.randomUUID().toString().toUpperCase()+suffix;
        File dest = new File(dir,fileName);//是一个空文件
        //将参数file中的数据写入到空文件dest中
        try {
            file.transferTo(dest);//将file文件中的数据写入到dest文件中 后缀要要一致
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }
        catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //返回头像的路径 /upload/test.png
        String avatar ="/upload/"+fileName;
       userService.changeAvatar(uid,avatar,username);
       //返回用户头像的路径给前端，将来用来头像展示使用
       return  new JsonResult<>(OK,avatar);

    }




}
