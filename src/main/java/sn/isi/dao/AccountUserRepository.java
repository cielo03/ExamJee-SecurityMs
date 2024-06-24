package sn.isi.dao;

import sn.isi.entity.AccountUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountUserRepository extends JpaRepository<AccountUserEntity, Long> {
	 Optional<AccountUserEntity> findByEmail(String email);
}
