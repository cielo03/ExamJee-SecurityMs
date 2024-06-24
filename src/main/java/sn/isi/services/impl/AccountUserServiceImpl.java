package sn.isi.services.impl;


import sn.isi.dto.AccountUserDto;
import sn.isi.entity.AccountUserEntity;
import sn.isi.dao.AccountUserRepository;
import sn.isi.services.AccountUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountUserServiceImpl implements AccountUserService {

    private final AccountUserRepository accountUserRepository;

    @Autowired
    public AccountUserServiceImpl(AccountUserRepository accountUserRepository) {
        this.accountUserRepository = accountUserRepository;
    }

    @Override
    public AccountUserDto save(AccountUserDto accountUserDto) {
        AccountUserEntity entity = new AccountUserEntity();
        BeanUtils.copyProperties(accountUserDto, entity);
        AccountUserEntity savedEntity = accountUserRepository.save(entity);
        return convertEntityToDto(savedEntity);
    }

    @Override
    public AccountUserDto getById(Long id) {
        AccountUserEntity entity = accountUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertEntityToDto(entity);
    }

    @Override
    public List<AccountUserDto> getAll() {
        List<AccountUserEntity> entities = accountUserRepository.findAll();
        return entities.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountUserDto update(AccountUserDto accountUserDto) {
        AccountUserEntity entity = accountUserRepository.findById(accountUserDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update entity properties with DTO values
        BeanUtils.copyProperties(accountUserDto, entity, "id");

        AccountUserEntity updatedEntity = accountUserRepository.save(entity);
        return convertEntityToDto(updatedEntity);
    }

    @Override
    public AccountUserDto findByEmail(String email) {
        AccountUserEntity entity = accountUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertEntityToDto(entity);
    }

    private AccountUserDto convertEntityToDto(AccountUserEntity entity) {
        AccountUserDto dto = new AccountUserDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}