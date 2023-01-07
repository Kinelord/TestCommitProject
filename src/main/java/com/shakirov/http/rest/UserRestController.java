package com.shakirov.http.rest;

import com.shakirov.dto.PageResponse;
import com.shakirov.dto.UserCreateEditDto;
import com.shakirov.dto.UserFilter;
import com.shakirov.dto.UserReadDto;
import com.shakirov.service.CompanyService;
import com.shakirov.service.UserService;
import com.shakirov.validation.group.CreateAction;
import com.shakirov.validation.group.UpdateAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.groups.Default;

/**
 * @author igor@shakirov-dev.ru on 30.12.2022
 * @version 1.0
 */

@Component
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserRestController {
	
	private final UserService userService;
	private final CompanyService companyService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	// PostFilter - отработает только на collection, array, map or stream type
//	@PostFilter("@companyService.findById(filterObject.companyId.id()).isPresent()")
	public PageResponse<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
		Page<UserReadDto> page = userService.findAll(filter, pageable);
		PageResponse<UserReadDto> response = PageResponse.of(page);
		return response;
	}
	
	
	@GetMapping("/{id}")
	@ResponseBody
	public UserReadDto findById(@PathVariable("id") Long id) {
		return userService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	/*	@GetMapping(value = "/{id}/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
		public byte[] findAvatar(@PathVariable("id") Long id) {
			return userService.findAvatar(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		}*/
	
	@GetMapping(value = "/{id}/avatar")
	public ResponseEntity<byte[]> findAvatar(@PathVariable("id") Long id) {
		return userService.findAvatar(id)
				.map(content -> ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
						.contentLength(content.length)
						.body(content))
				.orElseGet(ResponseEntity.notFound()::build);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public UserReadDto create(@Validated({Default.class, CreateAction.class}) @RequestBody UserCreateEditDto user) {
		return userService.create(user);
	}
	
	
	//	@PostMapping("/{id}/update")
	@PutMapping("/{id}")
	@ResponseBody
	public UserReadDto update(@PathVariable(name = "id") Long id,
	                          @Validated({Default.class, UpdateAction.class}) @RequestBody UserCreateEditDto userDto) {
		return userService.update(id, userDto)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
	}
	
	/*	//	@PostMapping("/{id}/delete")
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete(@PathVariable(name = "id") Long id) {
			
			if (!userService.delete(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}*/
//	@PostMapping("/{id}/delete")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		return userService.delete(id)
				? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}
	
}
