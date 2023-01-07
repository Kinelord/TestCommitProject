package com.shakirov.http.controller;

import com.shakirov.dto.PageResponse;
import com.shakirov.dto.UserCreateEditDto;
import com.shakirov.dto.UserFilter;
import com.shakirov.dto.UserReadDto;
import com.shakirov.model.Role;
import com.shakirov.service.CompanyService;
import com.shakirov.service.UserService;
import com.shakirov.validation.group.CreateAction;
import com.shakirov.validation.group.UpdateAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PostAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.groups.Default;

/**
 * @author igor@shakirov-dev.ru on 26.12.2022
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/users", name = "Controller For User")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserService userService;
	private final CompanyService companyService;


	@GetMapping
	public String findAll(Model model, UserFilter filter, Pageable pageable) {
		System.out.println("INit Controller");
//		model.addAttribute("users", userService.findAll());
//        model.addAttribute("users", userService.findAll(filter));
		Page<UserReadDto> page = userService.findAll(filter, pageable);
		PageResponse<UserReadDto> response = PageResponse.of(page);
		model.addAttribute("users", response);
		model.addAttribute("filter", filter);
		return "user/users";
	}

	@GetMapping(value = "/{id}")
	// PreAuthorize - Проводит проверку прав доступа, перед выполнением метода
//	@PreAuthorize("hasAuthority('ADMIN') and hasAuthority('MODERATOR')")
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public String findById(@PathVariable("id") Long id,
	                       Model model) {
		return userService.findById(id)
				.map(user -> {
					model.addAttribute("user", user);
					model.addAttribute("roles", Role.values());
					model.addAttribute("companies", companyService.findAll());
					return "user/user";
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/registration")
	public String registration(Model model, @ModelAttribute("user") UserCreateEditDto user) {
		model.addAttribute("user", user);
		model.addAttribute("roles", Role.values());
		model.addAttribute("companies", companyService.findAll());
		return "user/registration";
	}

	@PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
	public String create(@ModelAttribute @Validated({Default.class, CreateAction.class}) UserCreateEditDto user,
	                     BindingResult bindingResult,  // Должен идти сразу после объекта валидации, обязательно
	                     RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
//            redirectAttributes.addAttribute(UserCreateEditDto.Fields.username, user.getUsername());
//            redirectAttributes.addAttribute(firstname, user.getFirstname());
//            redirectAttributes.addAttribute(lastname, user.getLastname());
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/users/registration";
		}
//		return "redirect:/users/" + userService.create(user).getId();
		userService.create(user);
		return "redirect:/login";
	}

	//    @PutMapping("/{id}")
	@PostMapping("/{id}/update")
//	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
	public String update(@PathVariable(name = "id") Long id,
	                     @ModelAttribute @Validated({Default.class, UpdateAction.class}) UserCreateEditDto userDto) {
		return userService.update(id, userDto)
				.map(userReadDto -> "redirect:/users/{id}")
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

	}

	//    @DeleteMapping("/{id}")
	@PostMapping("/{id}/delete")
	// PostAuthorize - Проводит проверку прав доступа, после выполнения метода, и имеет доступ к возвращаемому значению
//	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
//	@PostAuthorize("returnObject.equals('redirect:/users')")
	public String delete(@PathVariable(name = "id") Long id) {

		if (userService.delete(id)) {
			return "redirect:/users";
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
