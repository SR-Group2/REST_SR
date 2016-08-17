package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Comments;
import org.khmeracademy.rest.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/get-comment/{rest_id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllComments(@PathVariable int rest_id){
		Map<String, Object> map= new Hashtable<String, Object>();
		try{
			ArrayList<Comments> comment= commentService.getAllComments(rest_id);
			if(!comment.isEmpty()){
				map.put("DATA", comment);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "Comment found");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "Comment not found");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/insert-comment", method= RequestMethod.POST)
	public  ResponseEntity<Map<String, Object>> insertComment(@RequestBody Comments comment){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(commentService.insertComment(comment)){
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "SUCCESSFULLY INSERTED");
			}else{
			 	map.put("STATUS", false);
				map.put("CODE", "404");
				map.put("MESSAGE", "Insert failed");
			}
			
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("CODE", "500");
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	@RequestMapping(method= RequestMethod.PUT)
	public  ResponseEntity<Map<String, Object>> updateComment(@RequestBody Comments comment){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(commentService.updateComment(comment)){
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "SUCCESSFULLY UPDATED");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "404");
				map.put("MESSAGE", "Update failed");
			}
			
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("CODE", "500");
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	@RequestMapping(value="/{comment-id}", method= RequestMethod.DELETE)
	public  ResponseEntity<Map<String, Object>> deleteComment(@PathVariable("comment-id") int id){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(commentService.deleteComment(id)){
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "SUCCESSFULLY DELETED");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "404");
				map.put("MESSAGE", "Delete failed");
			}
			
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("CODE", "500");
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	@RequestMapping(value="/{comment-id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getCommentById(@PathVariable("comment-id") int id){
		Map<String, Object> map= new Hashtable<String, Object>();
		try{
			Comments comment= commentService.getCommentById(id);
			if(!comment.equals(null)){
				map.put("DATA", comment);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "Comment found");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "Comment not found");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
