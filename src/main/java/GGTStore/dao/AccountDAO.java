package GGTStore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import GGTStore.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {

	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE', 'STAF')")
	List<Account> getAdminstrators();

	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('CUST')")
	List<Account> getUsers();

	@Query("SELECT COUNT(*) FROM Account")
	Account statisticalAccount();

	@Query(value = "select * from accounts where username = ?", nativeQuery = true)
	Optional<Account> findByUsername(String username);

	@Query(value = "select top 1 * from accounts where email = ?", nativeQuery = true)
	public Account findbyemail(String email);

	@Query(value = "select top 1 * from accounts where email = ?", nativeQuery = true)
	Account findByEmail(String email);

	@Query(value = "select * from accounts where username = ?", nativeQuery = true)
	Account findByUsernameContaining(String username);

}
