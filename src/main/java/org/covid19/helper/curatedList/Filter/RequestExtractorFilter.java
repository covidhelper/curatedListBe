//package org.covid19.helper.curatedList.Filter;
//
//import org.covid19.helper.curatedList.Constants.ApplicationConstants;
//import org.covid19.helper.curatedList.DTO.RequestExtractorDTO;
//import org.covid19.helper.curatedList.Util.ApplicationUtil;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Component
//@Order(1)
//public class RequestExtractorFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//        RequestExtractorDTO requestExtractorDTO = new RequestExtractorDTO();
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String cookie = httpServletRequest.getHeader(ApplicationConstants.SAMARITAN);
//        requestExtractorDTO.setIpAddress(httpServletRequest.getRemoteAddr());
//        if(cookie == null){
//            cookie = ApplicationUtil.uuidGenerator();
//        }
//        requestExtractorDTO.setCookie(cookie);
//
//    }
//}
