package org.zerock.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter{


	  @Override
	  public boolean preHandle(HttpServletRequest request, 
	      HttpServletResponse response, Object handler) throws Exception {
		  //파라메터 Object 는 ㄹㅇ 이제 실행될 메소드 자체를 뜻함-> 활용해서 현재 실행되는 컨트롤러 파악&&추가적인 메소드 실행 가능함. 
	    System.out.println("pre handle..........................");

	    HandlerMethod method = (HandlerMethod) handler;
	    Method methodObj = method.getMethod();

	    System.out.println("Bean: " + method.getBean());
	    System.out.println("Method: " + methodObj);

	    return true;

	  }
	//메소드 실행 끝나고 화면 처리는 안된 상태 
	  @Override
	  public void postHandle(HttpServletRequest request,
	      HttpServletResponse response, Object handler,
	      ModelAndView modelAndView) throws Exception {
	  
	    System.out.println("post handle........................");
	    
	    Object result = modelAndView.getModel().get("result");
	    
	    if(result != null){
	      System.out.println("result exists");
	     // System.out.println(result);
	      request.getSession().setAttribute("result", result);
	      response.sendRedirect("/doA");
	    }
	    //localhost:8080/doB 실행-> httpsession에 필요한 데이터 담고
	    //, doA의 uri를 리다이렉트 
	  }



	}


