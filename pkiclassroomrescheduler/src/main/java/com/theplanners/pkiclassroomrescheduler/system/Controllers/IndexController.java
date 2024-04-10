package com.theplanners.pkiclassroomrescheduler.system.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Index controller is a controller that correctly maps the homepage of the webapp
 * to /.
 */
@Controller
public class IndexController {

    @GetMapping("")
    public ModelAndView home() {
        ModelAndView mav=new ModelAndView("index");
        return mav;
    }

}