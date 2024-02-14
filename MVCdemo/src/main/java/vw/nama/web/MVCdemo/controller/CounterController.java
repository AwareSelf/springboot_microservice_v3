package vw.nama.web.MVCdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import vw.nama.web.MVCdemo.model.MyCounter;

@Controller
@RequestMapping("/counter")
@SessionAttributes("mycounter")
public class CounterController {

// Checks if there's a model attribute 'mycounter', if not create a new one.
  // Since 'mycounter' is labelled as session attribute it will be persisted to
  // HttpSession
  @RequestMapping(method = RequestMethod.GET)
  public String get(Model model) {
    if(!model.containsAttribute("mycounter")) {
      model.addAttribute("mycounter", new MyCounter(0));
    }
    return "counter";
  }

  // Obtain 'mycounter' object for this user's session and increment it
  @RequestMapping(method = RequestMethod.POST)
  public String post(@ModelAttribute("mycounter") MyCounter myCounter,SessionStatus sessionStatus) {
    myCounter.increment();
    if(myCounter.getValue()>=5)
    	sessionStatus.setComplete();
    return "redirect:/counter";
  }
}
