package com.shdmi.controller;

import com.shdmi.entity.Industryinformation;
import com.shdmi.entity.Works;
import com.shdmi.entity.WorksPicture;
import com.shdmi.enums.CategoryType;
import com.shdmi.enums.IndustryType;
import com.shdmi.service.IndustryinformationService;
import com.shdmi.service.WorksService;
import com.shdmi.utils.Page;
import com.shdmi.utils.ParamKey;
import com.shdmi.utils.QueryBuilder;
import com.shdmi.utils.UploadFileUtils;
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
import java.util.List;

/**
 * Created by shanghai on 2017/10/28.
 */
@Controller
@RequestMapping("/industryinformation")
public class IndustryinformationController extends BaseController{

    public final static String INDUSTRYINFORMATION_ADD = "/industryinformation/add";
    public final static String INDUSTRYINFORMATION_EDIT = "/industryinformation/edit";
    public final static String INDUSTRYINFORMATION_LIST = "/industryinformation/list";
    public final static String INDUSTRYINFORMATION_PICADD = "/industryinformation/picadd";
    public final static String INDUSTRYINFORMATION_PICEDIT = "/industryinformation/picedit";
    public final static String INDUSTRYINFORMATION_PICLIST = "/industryinformation/piclist";

    @Autowired
    private IndustryinformationService industryinformationService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("industryinformation", new Industryinformation());
        return INDUSTRYINFORMATION_ADD;
    }

    /**
     * 处理添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, Industryinformation industryinformation, @RequestParam("file") MultipartFile[] files, HttpServletRequest request,
                      RedirectAttributes redirectAttributes) throws Exception {
        if(files == null||files.length <= 0) {
            addRedirectError(redirectAttributes,"请选择图片");
            return getRedirectUrl(INDUSTRYINFORMATION_ADD);
        }
        String name = industryinformation.getName();
        if(StringUtils.isEmpty(name)) {
            addRedirectError(redirectAttributes,"请输入名称");
            return getRedirectUrl(INDUSTRYINFORMATION_ADD);
        }
        String date = industryinformation.getDate();
        if(StringUtils.isEmpty(date)) {
            addRedirectError(redirectAttributes,"请输入发布时间");
            return getRedirectUrl(INDUSTRYINFORMATION_ADD);
        }

        List<String> list = UploadFileUtils.savePicture(files);
        if(list == null||list.size() <=0){
            addRedirectError(redirectAttributes,"文件为空");
            return getRedirectUrl(INDUSTRYINFORMATION_ADD);
        }
        for(String s:list) {
            Industryinformation industryinformationNew = new Industryinformation();
            industryinformationNew.setCoverpic(s);
            industryinformationNew.setName(industryinformation.getName());
            industryinformationNew.setDate(industryinformation.getDate());
            industryinformationService.add(industryinformationNew);
        }
        return getRedirectUrl(INDUSTRYINFORMATION_LIST);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(HttpSession session, @PathVariable int id, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        Industryinformation industryinformation = industryinformationService.seleceIndustryinformationById(id);
        model.addAttribute("industryinformation", industryinformation);
        return INDUSTRYINFORMATION_EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpSession session, Industryinformation industryinformation, @RequestParam("file") MultipartFile[] files, HttpServletRequest request,
                       RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        if(files != null&&files.length > 0) {//重新上传了图片情况
            List<String> list = UploadFileUtils.savePicture(files);
            if (list != null&&list.size() > 0) {
                for (String s : list) {
                    Industryinformation industryinformationNew = new Industryinformation();
                    industryinformationNew.setId(industryinformation.getId());
                    industryinformationNew.setCoverpic(s);
                    industryinformationNew.setName(industryinformation.getName());
                    industryinformationNew.setDate(industryinformation.getDate());
                    industryinformationService.update(industryinformationNew);
                }
            }else{
                industryinformationService.update(industryinformation);
            }
        }else{//未重新上传图片情况
            industryinformationService.update(industryinformation);
        }
        addUpdateSuccessMessage(redirectAttributes);
        return getRedirectUrl(INDUSTRYINFORMATION_LIST);
    }

    @RequestMapping("/list")
    public String list(HttpSession session, HttpServletRequest request, Works works, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        QueryBuilder qb = QueryBuilder.newInstance("/industryinformation/list");
        int pageNo = ServletRequestUtils.getIntParameter(request, ParamKey.Page.NUM_KEY, ParamKey.Page.CURRENT_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, ParamKey.Page.SIZE_KEY, ParamKey.Page.PAGE_SIZE);
        int worksId = works.getId();
        Page<Industryinformation> industryinformationPage = industryinformationService.findByPage(pageNo, pageSize);
        model.addAttribute(ParamKey.Page.OBJECT, industryinformationPage);
        model.addAttribute(ParamKey.Page.SKIP_URL, qb.getPathWithQuery());
        model.addAttribute("industryinformations", industryinformationPage);
        return INDUSTRYINFORMATION_LIST;
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpSession session, HttpServletRequest request, @PathVariable int id, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        industryinformationService.delete(id);
        //删除图片操作
        List<Industryinformation> list = industryinformationService.selecePicByIndustryinformationId(id);
        if(list != null&&list.size() > 0){
            for(Industryinformation industryinformation:list) {
                industryinformationService.deletePic(industryinformation.getId());
                UploadFileUtils.deleteFile(industryinformation.getPath());
            }
        }
        addDeleteSuccessMessage(redirectAttributes);
        return getRedirectUrl(INDUSTRYINFORMATION_LIST);
    }

    @RequestMapping(value = "/picadd", method = RequestMethod.POST)
    public String add(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile[] files, RedirectAttributes redirectAttributes)
            throws Exception {
        String industryinformationIdString = request.getParameter("industryinformationId");
        int industryinformationId = Integer.parseInt(industryinformationIdString);
        if(industryinformationId <= 0) return getRedirectUrl(INDUSTRYINFORMATION_LIST);

        if(files == null||files.length <= 0) {
            addRedirectError(redirectAttributes,"请选择图片");
            return getRedirectUrl(INDUSTRYINFORMATION_PICLIST + "?industryinformationId=" + industryinformationId);
        }

        List<String> list = UploadFileUtils.savePicture(files);
        if(list == null||list.size() <=0){
            return "文件为空";
        }
        for(String s:list) {
            Industryinformation industryinformation = new Industryinformation();
            industryinformation.setPath(s);
            industryinformation.setIndustryinformationId(industryinformationId);
            industryinformationService.picAdd(industryinformation);
        }
        return getRedirectUrl(INDUSTRYINFORMATION_PICLIST + "?industryinformationId=" + industryinformationId);
    }

    @RequestMapping("/piclist")
    public String piclist(HttpSession session, HttpServletRequest request, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");
        String industryinformationIdString = request.getParameter("industryinformationId");
        int industryinformationId = Integer.parseInt(industryinformationIdString);
        if(industryinformationId <= 0) return getRedirectUrl(INDUSTRYINFORMATION_LIST);

        QueryBuilder qb = QueryBuilder.newInstance("/industryinformation/piclist");
        int pageNo = ServletRequestUtils.getIntParameter(request, ParamKey.Page.NUM_KEY, ParamKey.Page.CURRENT_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, ParamKey.Page.SIZE_KEY, ParamKey.Page.PAGE_SIZE);
        Page<Industryinformation> industryinformationPage = industryinformationService.findPicByPage(industryinformationId, pageNo, pageSize);
        model.addAttribute(ParamKey.Page.OBJECT, industryinformationPage);
        model.addAttribute(ParamKey.Page.SKIP_URL, qb.getPathWithQuery());
        model.addAttribute("industryinformationPic", industryinformationPage);
        model.addAttribute("industryinformationId", industryinformationId);
        return INDUSTRYINFORMATION_PICLIST;
    }

    @RequestMapping(value = "/picdelete/{id}&{industryinformationId}")
    public String deletepic(HttpSession session, HttpServletRequest request, @PathVariable int id,
                            @PathVariable int industryinformationId, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        //删除图片操作
        List<Industryinformation> list = industryinformationService.selecePicById(id);
        industryinformationService.deletePic(id);
        if(list != null&&list.size() > 0){
            for(Industryinformation industryinformation:list) {
                UploadFileUtils.deleteFile(industryinformation.getPath());
            }
        }
        addDeleteSuccessMessage(redirectAttributes);
        return getRedirectUrl(INDUSTRYINFORMATION_PICLIST + "?industryinformationId=" + industryinformationId);
    }

}
