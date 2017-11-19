package com.shdmi.controller;

import com.shdmi.entity.*;
import com.shdmi.enums.IndustryType;
import com.shdmi.enums.SinglePicType;
import com.shdmi.service.PortalsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 门户网站页面
 * Created by shanghai on 2017/10/17.
 */
@Controller
@RequestMapping(value = "/portals")
public class PortalsController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(PortalsController.class);
    public final static String HOME_PAGE = "/portals/home";
    public final static String WORK_PAGE = "/portals/work";
    public final static String WORKDETAIL_PAGE = "/portals/workdetail";
    public final static String ABOUT_PAGE = "/portals/about";
    public final static String CAREER_PAGE = "/portals/career";
    public final static String CONTACT_PAGE = "/portals/contact";
    public final static String INDUSTRYINFORMATION_PAGE = "/portals/industryinformation";
    public final static int INDUSTRYINFORMATIONSIZE = 3;//资讯显示条数

    @Autowired
    private PortalsService portalsService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model){
        //首页案例列表
        List<Works> homepageWorksList = portalsService.selectHomepageWorks();
        if(homepageWorksList != null&&homepageWorksList.size() > 0) {
            for (int i = 0; i < homepageWorksList.size(); i++) {
                List<WorksPicture> list = portalsService.selectWorksCoverpic(homepageWorksList.get(i).getId());
                if (list != null && list.size() > 0) homepageWorksList.get(i).setCoverPic(list.get(list.size() - 1).getPath());
            }
        }
        model.addAttribute("homepageWorksList", homepageWorksList);
        model.addAttribute("industryTypes", IndustryType.values());
        //首页一起合作大牌列表
        List<SinglePic> cooperatems = portalsService.selectPicByType(SinglePicType.COOPERATEMS.getCode());
        model.addAttribute("cooperatems", cooperatems);
        //行业资讯列表
        List<Industryinformation> industryinformations = portalsService.selectIndustryinformations(INDUSTRYINFORMATIONSIZE);
        model.addAttribute("industryinformations", industryinformations);
        return HOME_PAGE;
    }

    @RequestMapping(value = "/work", method = RequestMethod.GET)
    public String work(Model model){
        List<Works> worksList = portalsService.selectWorks();
        if(worksList != null&&worksList.size() > 0) {
            for (int i = 0; i < worksList.size(); i++) {
                List<WorksPicture> list = portalsService.selectWorksCoverpic(worksList.get(i).getId());
                if (list != null && list.size() > 0) worksList.get(i).setCoverPic(list.get(list.size() - 1).getPath());
            }
        }
        model.addAttribute("worksList", worksList);
        model.addAttribute("industryTypes", IndustryType.values());
        return WORK_PAGE;
    }

    @RequestMapping(value = "/workdetail/{id}", method = RequestMethod.GET)
    public String work(Model model, @PathVariable int id){
        Works work = portalsService.selectWork(id);
        List<WorksPicture> worksPictures = portalsService.selectWorkspic(id);
        model.addAttribute("work", work);
        model.addAttribute("worksPictures", worksPictures);
        model.addAttribute("industryTypes", IndustryType.values());
        return WORKDETAIL_PAGE;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model){
        return ABOUT_PAGE;
    }

    @RequestMapping(value = "/career", method = RequestMethod.GET)
    public String career(Model model){
        return CAREER_PAGE;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model){
        model.addAttribute("client", new Client());
        return CONTACT_PAGE;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String login(Model model, Client client, HttpServletRequest request) throws Exception {
        portalsService.add(client);
        model.addAttribute("client", new Client());
        return getRedirectUrl(CONTACT_PAGE);
    }

    @RequestMapping(value = "/industryinformation/{id}", method = RequestMethod.GET)
    public String industryinformation(Model model, @PathVariable int id){
        List<Industryinformation> industryinformations = portalsService.selectIndustryinformations(INDUSTRYINFORMATIONSIZE + 1);
        Industryinformation industryinformation = portalsService.selectIndustryinformationById(id);
        List<Industryinformation> industryinformationPics = portalsService.selectPicByInformationId(id);
        List<Industryinformation> industryinformationsOther = new ArrayList<>();
        for(int i=0;i<industryinformations.size();i++){
            if(industryinformationsOther.size() >= INDUSTRYINFORMATIONSIZE) break;
            if(industryinformations.get(i).getId() != industryinformation.getId()) industryinformationsOther.add(industryinformations.get(i));
        }
        model.addAttribute("industryinformationsOther", industryinformationsOther);
        model.addAttribute("industryinformation", industryinformation);
        model.addAttribute("industryinformationPics", industryinformationPics);
        return INDUSTRYINFORMATION_PAGE;
    }
}
