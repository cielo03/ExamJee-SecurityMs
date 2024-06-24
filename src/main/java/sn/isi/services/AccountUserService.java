package sn.isi.services;

import java.util.List;

import sn.isi.dto.AccountUserDto;

public interface AccountUserService {
	 AccountUserDto save(AccountUserDto accountUserDto);

	    AccountUserDto getById(Long id);

	    List<AccountUserDto> getAll();

	    AccountUserDto update(AccountUserDto accountUserDto);

	    AccountUserDto findByEmail(String email);
}
