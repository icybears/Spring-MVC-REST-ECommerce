package ma.pfa.webapp.security;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


 @Component
 public class AjaxCorsFilter extends GenericFilterBean {

/**
 * The Logger for this class.
 */
private final Logger logger = LoggerFactory.getLogger(this.getClass());

@Override
public void doFilter(ServletRequest req, ServletResponse resp,
                     FilterChain chain) throws IOException, ServletException {
    logger.info("> doFilter");

    HttpServletResponse response = (HttpServletResponse) resp;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
    //response.setHeader("Access-Control-Allow-Credentials", "true");
    chain.doFilter(req, resp);

    logger.info("< doFilter");
}
 }
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class AjaxCorsFilter extends CorsFilter {
//    public AjaxCorsFilter() {
//        super(configurationSource());
//    }
//
//    private static UrlBasedCorsConfigurationSource configurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//
//        // origins
//        config.addAllowedOrigin("*");
//
//        // when using ajax: withCredentials: true, we require exact origin match
//        config.setAllowCredentials(true);
//
//        // headers
//        config.addAllowedHeader("x-requested-with");
//
//        // methods
//        config.addAllowedMethod(HttpMethod.OPTIONS);
//        config.addAllowedMethod(HttpMethod.GET);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
//}