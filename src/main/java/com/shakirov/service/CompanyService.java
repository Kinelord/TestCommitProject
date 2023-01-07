package com.shakirov.service;

import com.shakirov.dto.CompanyReadDto;
import com.shakirov.database.repository.CompanyRepository;
import com.shakirov.mapper.CompanyReadMapper;
import com.shakirov.spting_pp.listener.AccessType;
import com.shakirov.spting_pp.listener.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional (readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private  CompanyRepository companyRepository;
    private  ApplicationEventPublisher eventPublisher;
    private final CompanyReadMapper companyReadMapper;


    public Optional<CompanyReadDto> findById (Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return companyReadMapper.map(entity);
                });
    }


    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyReadMapper::map)
                .toList();
    }



//    @Autowired
//    private  UserService userService;

/*    public CompanyService(CrudRepository<Integer, Company> companyCrudRepository, UserService userService, ApplicationEventPublisher eventPublisher) {
        this.companyCrudRepository = companyCrudRepository;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }*/

    //    @PostConstruct
    void init() {
//        System.out.println();
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @Autowired
    public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
}
