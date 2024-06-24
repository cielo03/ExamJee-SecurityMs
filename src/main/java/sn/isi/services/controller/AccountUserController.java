package sn.isi.services.controller;


import sn.isi.dto.AccountUserDto;
import sn.isi.services.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AccountUserController {
	private final AccountUserService accountUserService;

    @Autowired
    public AccountUserController(AccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountUserDto> save(@RequestBody AccountUserDto accountUserDto) {
        AccountUserDto savedDto = accountUserService.save(accountUserDto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AccountUserDto> getById(@PathVariable("id") Long id) {
        AccountUserDto dto = accountUserService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<AccountUserDto>> getAll() {
        List<AccountUserDto> dtos = accountUserService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountUserDto> update(@RequestBody AccountUserDto accountUserDto) {
        AccountUserDto updatedDto = accountUserService.update(accountUserDto);
        return ResponseEntity.ok(updatedDto);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AccountUserDto> findByEmail(@PathVariable("email") String email) {
        AccountUserDto dto = accountUserService.findByEmail(email);
        return ResponseEntity.ok(dto);
    }
}
