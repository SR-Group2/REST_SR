package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Votes;
import org.khmeracademy.rest.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
	@Autowired
	private VoteService voteService;
	
	@RequestMapping(value="/get-vote", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllVotes(){
		Map<String, Object> map= new Hashtable<String, Object>();
		try{
			ArrayList<Votes> votes= voteService.getAllVotes();
			if(!votes.isEmpty()){
				map.put("DATA", votes);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "Vote found");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "Vote not found");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/insert-vote", method= RequestMethod.POST)
	public  ResponseEntity<Map<String, Object>> insertUser(@RequestBody Votes vote){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(voteService.insertVote(vote)){
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
	ResponseEntity<Map<String, Object>> updateUser(@RequestBody Votes vote){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(voteService.updateVote(vote)){
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
	@RequestMapping(value="/{vote-id}", method= RequestMethod.DELETE)
	public  ResponseEntity<Map<String, Object>> deleteVote(@PathVariable("vote-id") int id){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(voteService.deleteVote(id)){
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
	@RequestMapping(value="/{vote-id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getVoteById(@PathVariable("vote-id") int id){
		Map<String, Object> map= new Hashtable<String, Object>();
		try{
			ArrayList<Votes> votes= voteService.getVoteById(id);
			if(!votes.isEmpty()){
				map.put("DATA", votes);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "Vote found");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "Vote not found");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
