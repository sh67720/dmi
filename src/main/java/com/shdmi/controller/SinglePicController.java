package com.shdmi.controller;

import com.shdmi.entity.SinglePic;
import com.shdmi.entity.Works;
import com.shdmi.entity.WorksPicture;
import com.shdmi.enums.CategoryType;
import com.shdmi.enums.IndustryType;
import com.shdmi.enums.SinglePicType;
import com.shdmi.service.SinglePicService;
import com.shdmi.service.WorksService;
import com.shdmi.utils.Page;
import com.shdmi.utils.ParamKey;
import com.shdmi.utils.QueryBuilder;
import com.shdmi.utils.UploadFileUtils;
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
 * Created by user on 2017/11/18.
 */
@Controller
@RequestMapping("/singlepic")
public class SinglePicController extends BaseController{

    public final static String SINGLEPIC_LIST = "/singlepic/list";

    @Autowired
    private SinglePicService singlePicService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, HttpServletRequest request, Byte singlePicType, @RequestParam("file") MultipartFile[] files, RedirectAttributes redirectAttributes)
            throws Exception {
        if(singlePicType == null) {
            addRedirectError(redirectAttributes,"请选择图片类型");
            return getRedirectUrl(SINGLEPIC_LIST);
        }
        if(files == null||files.length <= 0){
            addRedirectError(redirectAttributes,"请选择图片");
            return getRedirectUrl(SINGLEPIC_LIST);
        }

        List<String> list = UploadFileUtils.savePicture(files);
        if(list == null||list.size() <=0){
            addRedirectError(redirectAttributes,"图片为空");
            return getRedirectUrl(SINGLEPIC_LIST);
        }
        for(String s:list) {
            SinglePic singlePic = new SinglePic();
            singlePic.setPath(s);
            singlePic.setType(singlePicType);
            singlePicService.add(singlePic);
        }
        return getRedirectUrl(SINGLEPIC_LIST);
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpSession session, HttpServletRequest request, @PathVariable int id, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        //删除图片操作
        SinglePic singlePic = singlePicService.selecePicById(id);
        singlePicService.delete(id);
        UploadFileUtils.deleteFile(singlePic.getPath());
        addDeleteSuccessMessage(redirectAttributes);
        return getRedirectUrl(SINGLEPIC_LIST);
    }

    @RequestMapping("/list")
    public String list(HttpSession session, HttpServletRequest request, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        QueryBuilder qb = QueryBuilder.newInstance("/singlepic/list");
        int pageNo = ServletRequestUtils.getIntParameter(request, ParamKey.Page.NUM_KEY, ParamKey.Page.CURRENT_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, ParamKey.Page.SIZE_KEY, ParamKey.Page.PAGE_SIZE);

        Page<SinglePic> singlePicPage = singlePicService.selecePic(pageNo, pageSize);
        model.addAttribute(ParamKey.Page.OBJECT, singlePicPage);
        model.addAttribute(ParamKey.Page.SKIP_URL, qb.getPathWithQuery());
        model.addAttribute("singlePic", singlePicPage);
        model.addAttribute("singlePicType", SinglePicType.values());
        return SINGLEPIC_LIST;
    }
}
