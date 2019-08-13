package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

@RestController
@RequestMapping("/sample")
public class SampleController {
	
@RequestMapping("/hello")
public String sayHello() {
	return "Hello World";
}

@RequestMapping("/sendVO")
public SampleVO sendVO(){//객체를 자동으로 json처리하여 반환
	
	SampleVO vo = new SampleVO();
	vo.setFirstName("길동");
	vo.setLastName("홍");
	vo.setMno(123);
	
	return vo;
	}

@RequestMapping("/sendList")
public List<SampleVO> sendList() { //List 결과 데이터 
	
	List<SampleVO> list = new ArrayList<>();
	for(int i=0;i<10;i++) {
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(i);
		list.add(vo);
	}
	return list;
}

@RequestMapping("/sendMap")
public Map<Integer, SampleVO> sendMap() {//Map 결과 데이터
	
	Map<Integer, SampleVO> map = new HashMap<>();
	for(int i=0;i<10;i++) {
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(i);
		map.put(i,vo);
	}
	return map;
}

@RequestMapping("/sendErrorAuth")
public ResponseEntity<Void> sendListAuth() { //400-500에러에 대한 세밀한 제어 

  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}

@RequestMapping("/sendErrorNot")
public ResponseEntity<List<SampleVO>> sendListNot() {

  List<SampleVO> list = new ArrayList<>();
  for (int i = 0; i < 10; i++) {
    SampleVO vo = new SampleVO();
    vo.setFirstName("길동");
    vo.setLastName("홍");
    vo.setMno(i);
    list.add(vo);
  }

  return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);

}
}


