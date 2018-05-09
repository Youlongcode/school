package com.student.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class HelloController {

//	@Autowired
//	private Student student;
	
//	@RequestMapping(value= {"save","school"},method=RequestMethod.GET)
	@GetMapping(value="/save")
	public String save(@RequestParam(value="id",required = false,defaultValue="0") Integer id) {
		return "id:"+id;
//		return student.getCupSize();
	}
}
