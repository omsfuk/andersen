package cn.omsfuk.andersen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/19
 */

@Controller
public class BasicController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    
    @RequestMapping(value = "archive", method = RequestMethod.GET)
    public String archive() {
        return "archive";
    }
    
    @RequestMapping(value = "post", method = RequestMethod.GET)
    public String post() {
        return "post";
    }

}
