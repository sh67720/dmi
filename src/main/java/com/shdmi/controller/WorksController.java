package com.shdmi.controller;

import com.shdmi.entity.*;
import com.shdmi.enums.CategoryType;
import com.shdmi.enums.IndustryType;
import com.shdmi.enums.ProjectBottomTag;
import com.shdmi.enums.ProjectTopTag;
import com.shdmi.service.PortalsService;
import com.shdmi.service.WorksService;
import com.shdmi.utils.MD5Util;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shanghai on 2017/10/28.
 */
@Controller
@RequestMapping("/works")
public class WorksController extends BaseController{

    public final static String WORKS_ADD = "/works/add";
    public final static String WORKS_EDIT = "/works/edit";
    public final static String WORKS_LIST = "/works/list";
    public final static String WORKS_PICADD = "/works/picadd";
    public final static String WORKS_PICEDIT = "/works/picedit";
    public final static String WORKS_PICLIST = "/works/piclist";

    @Autowired
    private WorksService worksService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("categoryTypes", CategoryType.values());
        model.addAttribute("industryTypes", IndustryType.values());
        model.addAttribute("works", new Works());
        return WORKS_ADD;
    }

    /**
     * 处理添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, Works works, HttpServletRequest request) throws Exception {
        worksService.add(works);
        return getRedirectUrl(WORKS_LIST);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(HttpSession session, @PathVariable int id, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        Works works = worksService.getFromDB(id);
        if(works.getBrandIntroduction()==null)works.setBrandIntroduction("");
        if(works.getRemark()==null)works.setRemark("");
        model.addAttribute("categoryTypes", CategoryType.values());
        model.addAttribute("industryTypes", IndustryType.values());
        model.addAttribute("works", works);
        return WORKS_EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpSession session, Works works, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        worksService.update(works);
        addUpdateSuccessMessage(redirectAttributes);
        return getRedirectUrl(WORKS_LIST);
    }

    @RequestMapping("/list")
    public String list(HttpSession session, HttpServletRequest request, Works works, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        QueryBuilder qb = QueryBuilder.newInstance("/works/list");
        int pageNo = ServletRequestUtils.getIntParameter(request, ParamKey.Page.NUM_KEY, ParamKey.Page.CURRENT_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, ParamKey.Page.SIZE_KEY, ParamKey.Page.PAGE_SIZE);
        int worksId = works.getId();
        Page<Works> worksPage = worksService.findByPage(pageNo, pageSize);
        model.addAttribute(ParamKey.Page.OBJECT, worksPage);
        model.addAttribute(ParamKey.Page.SKIP_URL, qb.getPathWithQuery());
        model.addAttribute("works", worksPage);
        model.addAttribute("categoryTypes", CategoryType.values());
        model.addAttribute("industryTypes", IndustryType.values());
        return WORKS_LIST;
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpSession session, HttpServletRequest request, @PathVariable int id, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        worksService.delete(id);
        //删除图片操作
        List<WorksPicture> list = worksService.selecePicWorkcaseId(id);
        if(list != null&&list.size() > 0){
            for(WorksPicture worksPicture:list) {
                worksService.picDelete(worksPicture.getId());
                deleteFile(request, worksPicture.getPath());
            }
        }
        addDeleteSuccessMessage(redirectAttributes);
        return getRedirectUrl(WORKS_LIST);
    }

    @RequestMapping(value = "/picadd", method = RequestMethod.POST)
    public String add(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile[] files)
            throws Exception {
        String worksIdString = request.getParameter("worksId");
        int worksId = Integer.parseInt(worksIdString);
        if(worksId <= 0) return getRedirectUrl(WORKS_LIST);
        String iscoverString = request.getParameter("iscover");
        boolean iscover = Boolean.parseBoolean(iscoverString);

        List<String> list = savePicture(request, files);
        if(list == null||list.size() <=0){
            return "文件为空";
        }
        for(String s:list) {
            WorksPicture worksPicture = new WorksPicture();
            worksPicture.setPath(s);
            worksPicture.setWorkcaseId(worksId);
            worksPicture.setIscover(iscover);
            worksService.picAdd(worksPicture);
        }
        return getRedirectUrl(WORKS_PICLIST + "?worksId=" + worksId);
    }

    @RequestMapping("/piclist")
    public String piclist(HttpSession session, HttpServletRequest request, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");
        String worksIdString = request.getParameter("worksId");
        int worksId = Integer.parseInt(worksIdString);
        if(worksId <= 0) return getRedirectUrl(WORKS_LIST);

        QueryBuilder qb = QueryBuilder.newInstance("/works/piclist");
        int pageNo = ServletRequestUtils.getIntParameter(request, ParamKey.Page.NUM_KEY, ParamKey.Page.CURRENT_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, ParamKey.Page.SIZE_KEY, ParamKey.Page.PAGE_SIZE);
        Page<WorksPicture> worksPicturePage = worksService.findPicByPage(worksId, pageNo, pageSize);
        model.addAttribute(ParamKey.Page.OBJECT, worksPicturePage);
        model.addAttribute(ParamKey.Page.SKIP_URL, qb.getPathWithQuery());
        model.addAttribute("worksPicture", worksPicturePage);
        model.addAttribute("worksId", worksId);
        return WORKS_PICLIST;
    }

    @RequestMapping(value = "/picdelete/{id}&{worksId}")
    public String deletepic(HttpSession session, HttpServletRequest request, @PathVariable int id,
                            @PathVariable int worksId, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        //删除图片操作
        List<WorksPicture> list = worksService.selecePicId(id);
        worksService.picDelete(id);
        if(list != null&&list.size() > 0){
            for(WorksPicture worksPicture:list) {
                deleteFile(request, worksPicture.getPath());
            }
        }
        addDeleteSuccessMessage(redirectAttributes);
        return getRedirectUrl(WORKS_PICLIST + "?worksId=" + worksId);
    }

    public List<String> savePicture(HttpServletRequest request, MultipartFile[] files){
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

    private void deleteFile(HttpServletRequest request, String path){
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(rootPath + path);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + rootPath+path + "成功！");
            } else {
                System.out.println("删除单个文件" + rootPath+path + "失败！");
            }
        } else {
            System.out.println("删除单个文件失败：" + rootPath+path + "不存在！");
        }
    }
}
