package org.springframework.samples.drink_safe.system;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller that handles if the server crashes
 * 
 * @author Jeremy and Nick
 *
 */
@RestController
class CrashController {

    @RequestMapping(method = RequestMethod.GET, path = "/oups")
    public String triggerException() {
        throw new RuntimeException("Expected: controller used to showcase what "
                + "happens when an exception is thrown");
    }

}