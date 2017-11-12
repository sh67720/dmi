package com.shdmi.controller;

import com.shdmi.entity.Apply;
import com.shdmi.entity.Project;
import com.shdmi.enums.ApplyType;
import com.shdmi.service.ApplyService;
import com.shdmi.service.ProjectService;
import com.shdmi.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by vincent on 16/3/20.
 */
@Controller
@RequestMapping("/apply")
public class ApplyController extends BaseController{

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("/{projectId}/{type}")
    public String apply(@PathVariable byte type, @PathVariable int projectId, Model model, HttpServletRequest request){
        Apply apply = new Apply();
        apply.setType(type);
        apply.setProjectId(projectId);
        String telphone = projectService.get(projectId).getTelphone();
        String telphone1 = projectService.get(projectId).getTelphone1();
        model.addAttribute("telphone", telphone);
        model.addAttribute("telphone1", telphone1);
        model.addAttribute("apply", apply);
        model.addAttribute("applyTypes", ApplyType.getApplyTypeMap());

        boolean isFromMobile=false;
        HttpSession session= request.getSession();
        if(null==session.getAttribute("ua")){
            try{
                //获取ua，用来判断是否为移动端访问
                String userAgent = request.getHeader( "USER-AGENT" ).toLowerCase();
                if(null == userAgent){
                    userAgent = "";
                }
                isFromMobile= CheckMobile.check(userAgent);
                //判断是否为移动端访问
                if(isFromMobile){
                    return "/project/mobileApply";
                } else {
                    return "/project/pcApply";
                }
            }catch(Exception e){}
        }else {
            isFromMobile=session.getAttribute("ua").equals("mobile");
            if(isFromMobile){
                return "/project/mobileApply";
            } else {
                return "/project/pcApply";
            }
        }
        return "/project/pcApply";
    }

    @RequestMapping
    public @ResponseBody String apply(Apply apply){
        if (apply == null || apply.getProjectId() == 0) return "系统错误!";
        if (StringUtils.isEmpty(apply.getName()) || apply.getName().equals("输入您的姓名(必填)")) return "姓名不能为空!";
        if (StringUtils.isEmpty(apply.getPhone()) || apply.getPhone().equals("输入您的手机号(必填)")) return "手机号不能为空!";
        if (!Regex.isMobileNo(apply.getPhone())) return "手机号格式有误!";
        if (applyService.exist(apply.getPhone()) > 0) return "您已报名成功!请勿重复提交!";
        applyService.add(apply);
        Project project = projectService.get(apply.getProjectId());
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("xxxxxx@163.com");
        mail.setTo(project.getMail());
        mail.setSubject(project.getSubTitle()+" 报名信息");
        StringBuilder sb = new StringBuilder();
        String subTitle = StringUtils.isEmpty(project.getSubTitle())?"":project.getSubTitle();
        sb.append("项目:").append(subTitle).append(", 姓名:").append(apply.getName())
                .append(", 手机号:").append(apply.getPhone());
        if (StringUtils.isNotEmpty(apply.getAddress()))
                sb.append(", 地址:").append(apply.getAddress());
        mail.setText(sb.toString());
        mailSender.send(mail);
        return "ok";
    }

    @RequestMapping("/list")
    public String list(HttpSession session, HttpServletRequest request, Apply apply, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        QueryBuilder qb = QueryBuilder.newInstance("/apply/list");
        int pageNo = ServletRequestUtils.getIntParameter(request, ParamKey.Page.NUM_KEY, ParamKey.Page.CURRENT_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, ParamKey.Page.SIZE_KEY, ParamKey.Page.PAGE_SIZE);
        int projectId = apply.getProjectId();
        if (projectId != 0) {
            qb.addQuery("projectId", projectId);
        }
        Page<Apply> applyPage = applyService.findByPage(projectId, pageNo, pageSize);
        model.addAttribute(ParamKey.Page.OBJECT, applyPage);
        model.addAttribute(ParamKey.Page.SKIP_URL, qb.getPathWithQuery());
        model.addAttribute("apply", apply);
        return "/apply/list";
    }
}
