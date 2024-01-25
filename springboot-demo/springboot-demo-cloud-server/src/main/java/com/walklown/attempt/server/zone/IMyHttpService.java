package com.walklown.attempt.server.zone;

import org.springframework.web.service.annotation.GetExchange;

/**
 * Http interface client
 *
 * @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public interface IMyHttpService {

    @GetExchange(url = "/my-http/hello")
    String hello();
}
