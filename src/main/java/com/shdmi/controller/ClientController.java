package com.shdmi.controller;

import com.shdmi.entity.Apply;
import com.shdmi.entity.Client;
import com.shdmi.entity.Works;
import com.shdmi.service.PortalsService;
import com.shdmi.utils.Page;
import com.shdmi.utils.ParamKey;
import com.shdmi.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by shanghai on 2017/10/27.
 */
@Controller
@RequestMapping("/client")
public class ClientController extends BaseController{

    @Autowired
    private PortalsService portalsService;

    @RequestMapping("/list")
    public String list(HttpSession session, HttpServletRequest request, Client client, Model model) {
        if (session.getAttribute("admin") == null) return getRedirectUrl("/outward/login");

        QueryBuilder qb = QueryBuilder.newInstance("/client/list");
        int pageNo = ServletRequestUtils.getIntParameter(request, ParamKey.Page.NUM_KEY, ParamKey.Page.CURRENT_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, ParamKey.Page.SIZE_KEY, ParamKey.Page.PAGE_SIZE);
        int clientId = client.getId();
        Page<Client> clientPage = portalsService.findByPage(pageNo, pageSize);
        model.addAttribute(ParamKey.Page.OBJECT, clientPage);
        model.addAttribute(ParamKey.Page.SKIP_URL, qb.getPathWithQuery());
        model.addAttribute("client", client);
        return "/client/list";
    }
}
