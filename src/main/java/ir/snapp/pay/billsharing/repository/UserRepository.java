package ir.snapp.pay.billsharing.repository;

import ir.snapp.pay.billsharing.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
}
