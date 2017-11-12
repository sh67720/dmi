package com.shdmi.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vincent on 16/3/22.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping
    public @ResponseBody
    List<String> upload(HttpServletRequest request, @RequestParam("file") MultipartFile[] files) {
        List<String> names = null;
        //判断file数组不能为空并且长度大于0
        if(files != null && files.length > 0){
            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            names = new ArrayList<>();
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                //保存文件
                String name = saveFile(request, file, date);
                if (StringUtils.isNotEmpty(name))
                    names.add(name);
            }
        }
        return names;
    }

    /***
     * 保存文件
     * @param file
     * @return
     */
    private String saveFile(HttpServletRequest request, MultipartFile file, String date) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String name = file.getOriginalFilename();
                // 文件保存路径
                String rootPath = request.getSession().getServletContext().getRealPath("/");
                String filePath = "/upload/" + date + "/" + System.nanoTime() + name.substring(name.lastIndexOf("."));
                File fileTo = new File(rootPath + filePath);
                if (!fileTo.getParentFile().exists())
                    fileTo.mkdirs();
                // 转存文件
                file.transferTo(fileTo);
                return filePath;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
