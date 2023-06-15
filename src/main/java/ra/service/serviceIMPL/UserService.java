package ra.service.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.entity.user.Users;
import ra.repository.IUserrepository;
import ra.service.IUserService;

import java.util.List;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserrepository userrepository;
    @Override
    public List<Users> findAll() {
        return userrepository.findAll();
    }

    @Override
    public Users save(Users users) {
       return   userrepository.save(users);

    }

    @Override
    public void deleteById(Long id) {
        userrepository.deleteById(id);

    }

    @Override
    public Users findById(Long id) {
        return userrepository.findById(id).get();
    }



    @Override
    public Users findByUsername(String username) {
        return userrepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userrepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userrepository.existsByEmail(email);
    }
}
