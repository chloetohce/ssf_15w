package sg.ed.nus.iss.ssf_13w.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping()
    @ResponseBody
    public String test(@Value("${dataDir}") String data) {
        System.out.println(data);
        return data;
    }
    

}
