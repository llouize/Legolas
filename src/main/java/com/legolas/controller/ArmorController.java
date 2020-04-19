package com.legolas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legolas.bean.Armor;
import com.legolas.dao.ArmorDAO;

@RestController
@RequestMapping("/auth")
public class ArmorController {

	@Autowired
	ArmorDAO ad;
	
	@PostMapping("/armor")
	public Armor add(@Valid @RequestBody Armor a) {
		return ad.save(a);
	}
	
	@GetMapping("/armor")
	public List<Armor> getAllArmor(){
		return ad.findAll();
		
	}
	@GetMapping("/armor/{item_id}")
	public ResponseEntity<Armor> getArmorById(@PathVariable(value = "item_id") Long item_id){
		Armor a = ad.findOne(item_id);

		if (item_id == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(a);
	}
	@PutMapping("/armor/{item_id}")
	public ResponseEntity<Armor> updateArmor(@PathVariable(value = "item_id") Long item_id, @Valid @RequestBody Armor a) {

		Armor r = ad.findOne(item_id);

		if (r == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(a, r, "id");

		r = ad.save(r);

		return ResponseEntity.ok(r);
	}
	@DeleteMapping("/armor/{item_id}")
	public ResponseEntity<Armor> delete(@PathVariable(value = "item_id") Long item_id) {
		Armor a = ad.findOne(item_id);

		if (a == null) {
			ResponseEntity.notFound().build();
		}

		ad.delete(a);

		return ResponseEntity.ok().build();
	}
}