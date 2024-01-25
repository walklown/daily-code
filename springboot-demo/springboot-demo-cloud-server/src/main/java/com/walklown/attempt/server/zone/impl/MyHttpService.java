package com.walklown.attempt.server.zone.impl;


import com.walklown.attempt.server.zone.IMyHttpService;
import io.microsphere.multiple.active.zone.ZoneConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTP interface
 *
 * @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
@RestController
public class MyHttpService implements IMyHttpService {

    @Value("${" + ZoneConstants.ZONE_PROPERTY_NAME + "}")
    private String zone;

    @Override
    public String hello() {
//        return "Hello, " + name + " !";
        return "Hello[" + zone + "]";
    }
}
