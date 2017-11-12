package com.shdmi.controller;

import com.shdmi.entity.Project;
import com.shdmi.enums.ProjectBottomTag;
import com.shdmi.enums.ProjectTopTag;
import com.shdmi.service.ProjectService;
import com.shdmi.utils.CheckMobile;
import com.shdmi.utils.Page;
import com.shdmi.utils.ParamKey;
import com.shdmi.utils.QueryBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by vincent on 16/3/19.
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController{

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/{id}")
    public String detail(@PathVariable int id, Model model, HttpServletRequest request){
        Project project = projectService.get(id);
        model.addAttribute("project", project);
        model.addAttribute("topTagColors", ProjectTopTag.getColorMap());
        model.addAttribute("bottomTagColors", ProjectBottomTag.getColorMap());

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
                    return "/project/mobile";
                } else {
                    return "/project/pc1";
                }
            }catch(Exception e){}
        }else {
            isFromMobile=session.getAttribute("ua").equals("mobile");
            if(isFromMobile){
                return "/project/mobile";
            } else {
                return "/project/pc1";
            }
        }
        return "/project/pc1";
    }

    @RequestMapping("/pc/{id}")
    public String detail1(@PathVariable int id, Model model, HttpServletRequest request) {
        Project project = projectService.get(id);
        model.addAttribute("project", project);
        model.addAttribute("topTagColors", ProjectTopTag.getColorMap());
        model.addAttribute("bottomTagColors", ProjectBottomTag.getColorMap());
        return "/project/pc1";
    }

    @RequestMapping("/list")
    public String list(HttpSession session, HttpServletRequest request, Project project, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        QueryBuilder qb = QueryBuilder.newInstance("/project/list");
        int pageNo = ServletRequestUtils.getIntParameter(request, ParamKey.Page.NUM_KEY, ParamKey.Page.CURRENT_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, ParamKey.Page.SIZE_KEY, ParamKey.Page.PAGE_SIZE);
        String title = project.getTitle();
        if (StringUtils.isNotEmpty(title)) {
            qb.addQuery("title", title);
        }
        Page<Project> projectPage = projectService.findByPage(title, pageNo, pageSize);
        model.addAttribute(ParamKey.Page.OBJECT, projectPage);
        model.addAttribute(ParamKey.Page.SKIP_URL, qb.getPathWithQuery());
        model.addAttribute("project", project);
        return "/project/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(HttpSession session, @PathVariable int id, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        Project project = projectService.getFromDB(id);
        model.addAttribute("topTags", ProjectTopTag.getNameMap());
        model.addAttribute("bottomTags", ProjectBottomTag.getNameMap());
        model.addAttribute("project", project);
        return "/project/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpSession session, Project project, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        projectService.update(project);
        addUpdateSuccessMessage(redirectAttributes);
        return getRedirectUrl("/project/list");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpSession session, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        model.addAttribute("topTags", ProjectTopTag.getNameMap());
        model.addAttribute("bottomTags", ProjectBottomTag.getNameMap());
        model.addAttribute("project", new Project());
        return "/project/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpSession session, Project project, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        projectService.add(project);
        addSaveSuccessMessage(redirectAttributes);
        return getRedirectUrl("/project/list");
    }

    @RequestMapping(value = "/delete/{id}")
    public String add(HttpSession session, @PathVariable int id, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        projectService.delete(id);
        addDeleteSuccessMessage(redirectAttributes);
        return getRedirectUrl("/project/list");
    }
}