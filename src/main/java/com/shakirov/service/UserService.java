package com.shakirov.service;

import com.querydsl.core.types.Predicate;
import com.shakirov.database.querydsl.QPredicates;
import com.shakirov.database.repository.UserRepository;
import com.shakirov.dto.UserCreateEditDto;
import com.shakirov.dto.UserFilter;
import com.shakirov.dto.UserReadDto;
import com.shakirov.mapper.UserCreateEditMapper;
import com.shakirov.mapper.UserReadMapper;
import com.shakirov.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.shakirov.model.QUser.user;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	
	//    @Resource
	private final UserRepository userRepository;
	private final UserReadMapper userReadMapper;
	private final UserCreateEditMapper userCreateEditMapper;
	private final ImageService imageService;
	
	public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
		
		Predicate predicate = QPredicates.builder()
				.add(filter.firstname(), user.firstname::containsIgnoreCase)
				.add(filter.lastname(), user.lastname::containsIgnoreCase)
				.add(filter.birthDate(), user.birthDate::before)
				.buildAnd();
		Page<User> all = userRepository.findAll(predicate, pageable);
		return all.map(userReadMapper::map);
	}
    
    // @Фильтры пост и пре не рекомендуется применять, т.к. лучше фильтровать все запросы на уровне запросов к БД или в коде
	// Фильтрует результат работы метода, по установленным значениям (т.е. в данном примере мы вернем только тех
    // пользователей, кто работает в существующей компании
	
//	@PostFilter("@companyService.findById(filterObject.companyId.id()).isPresent()")
	public List<UserReadDto> findAll(UserFilter filter) {
		
		return userRepository.findAllByFilterForQueryDSL(filter).stream()
				.map(userReadMapper::map)
				.toList();
	}
	
	// Фильтрует результат работы метода, по установленным значениям (т.е. в данном примере мы вернем только тех
	// пользователей, кто имеет роль админ
//	@PostFilter("filterObject.role.name().equals('ADMIN')")
	public List<UserReadDto> findAll() {
		return userRepository.findAll().stream()
				.map(userReadMapper::map)
				.toList();
	}
	
	
//	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public Optional<UserReadDto> findById(Long id) {
		return userRepository.findById(id)
				.map(userReadMapper::map);
	}
	
	public Optional<byte[]> findAvatar(Long id) {
		return userRepository.findById(id)
				.map(User::getImage)
				.filter(StringUtils::hasText)
				.flatMap(imageService::get);
	}
	
	@Transactional
//	@PreFilter("not @hasAuthority('ADMIN')")
	public UserReadDto create(UserCreateEditDto userDto) {
		return Optional.of(userDto)
				.map(dto -> {
					uploadImage(dto.getImage());
					return userCreateEditMapper.map(dto);
				})
//                .map(userRepository::saveAllAndFlush);
				.map(userRepository::saveAndFlush)
				.map(userReadMapper::map)
				.orElseThrow();
	}
	
	@Transactional
	public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
		return userRepository.findById(id)
				.map(entity -> {
					uploadImage(userDto.getImage());
					return userCreateEditMapper.map(userDto, entity);
				})
				.map(userRepository::saveAndFlush)
				.map(userReadMapper::map);
	}
	
	//    @SneakyThrows
	private void uploadImage(MultipartFile image) {
		if (!image.isEmpty()) {
			try {
				imageService.upload(image.getOriginalFilename(), image.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Transactional
	public boolean delete(Long id) {
		return userRepository.findById(id)
				.map(entity -> {
					userRepository.delete(entity);
					userRepository.flush();
					return true;
				})
				.orElse(false);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.map(user -> new org.springframework.security.core.userdetails.User(
						user.getUsername(),
						user.getPassword(),
						Collections.singleton(user.getRole())
				))
				.orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
	}
	
	//    @Autowired
//    private final CompanyRepository companyRepository;


/*    public UserService(UserRepository userRepository, CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }*/
	
	//    @PostConstruct
	private void initPostConstruct() {
//        System.out.println("Post Construct");
	}
	
}
