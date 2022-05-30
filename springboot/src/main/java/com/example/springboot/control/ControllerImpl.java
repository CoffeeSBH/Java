package com.example.springboot.control;

import com.example.springboot.control.dto.JsonObject;
import com.example.springboot.control.dto.LoginDo;
import com.example.springboot.control.dto.UserInfoDo;
import com.example.springboot.mapper.UserInfoMapper;
import com.example.springboot.mapper.entity.UserInfoEntity;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 23265
 */
@Controller
public class ControllerImpl {
    @Resource
    protected HttpServletRequest request;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Autowired
    private Producer captchaProducer = null;


    @PostMapping("/user_register")
    @ResponseBody
    public JsonObject register(UserInfoDo userInfoDo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        JsonObject<String> obj = new JsonObject();
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if("".equals(code) ||code==null){
            obj.setCode(-1);
            obj.setMessage("请不要重复提交！");
            return obj;
        }
        if (!code.equals(userInfoDo.getCode())){
            obj.setCode(2);
            obj.setMessage("验证码输入有误！");
            return obj;
        }
        HashMap<String, Object> inMap = new HashMap<>();
        inMap.put("USERNAME", userInfoDo.getUserName());
        List<UserInfoEntity> userInfoList = userInfoMapper.selectSelective(inMap);
        if (CollectionUtils.isEmpty(userInfoList)) {
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            Date date = new Date();
            userInfoEntity.setUsername(userInfoDo.getUserName());
            userInfoEntity.setPassword(userInfoDo.getPassWord());
            userInfoEntity.setTelephone(userInfoDo.getTelephone());
            userInfoEntity.setRegistertime(date);
            userInfoEntity.setPopedom("1");
            Integer count = userInfoMapper.insertSelective(userInfoEntity);
            if (count > 0) {
                inMap.put("USERNAME", userInfoEntity.getUsername());
                inMap.put("PASSWORD", userInfoEntity.getPassword());
                List<UserInfoEntity> userInfo = userInfoMapper.selectSelective(inMap);
                session.setAttribute("users", userInfo);
                session.setAttribute("user", userInfo.get(0));
                session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
                obj.setCode(0);
                obj.setMessage("注册成功！");
            }
        } else {
            obj.setCode(1);
            obj.setMessage("用户名已存在！");
        }
        return obj;
    }

    @PostMapping("/user_login")
    @ResponseBody
    public JsonObject login(String username, String password, HttpServletRequest request) {
        JsonObject<String> obj = new JsonObject();
        HashMap<String, Object> inMap = new HashMap<>();
        if ("".equals(username)||username==null||"".equals(password)||password==null){
            obj.setCode(1);
            obj.setMessage("入参username和password均不能为空");
        }
        inMap.put("USERNAME", username);
        inMap.put("PASSWORD", password);
        List<UserInfoEntity> userInfoList = userInfoMapper.selectSelective(inMap);
        HttpSession session = request.getSession();
        if (!CollectionUtils.isEmpty(userInfoList)) {
//            session.setAttribute("users", userInfoList);
            session.setAttribute("user", userInfoList.get(0));
            obj.setCode(0);
        } else {
            obj.setCode(1);
            obj.setMessage("用户名或密码错误");
        }
        return obj;
    }

    @PostMapping("/checkUserName")
    @ResponseBody
    public JsonObject checkUserName(String username) {
        JsonObject<String> obj = new JsonObject();
        HashMap<String, Object> inMap = new HashMap<>();
        inMap.put("USERNAME", username);
        List<UserInfoEntity> userInfoList = userInfoMapper.selectSelective(inMap);
        if (CollectionUtils.isEmpty(userInfoList)) {
            obj.setCode(0);
            obj.setMessage("用户名可用");
        } else {
            obj.setCode(1);
            obj.setMessage("用户名已存在");
        }
        return obj;
    }



    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\23265\\Pictures\\Saved Pictures";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
        }
        return "上传失败！";
    }

    @RequestMapping("/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}

