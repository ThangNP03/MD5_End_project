package ra.service.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.entity.user.RoleName;
import ra.entity.user.Roles;
import ra.repository.IRoleRepository;
import ra.service.IRoleService;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Roles save(Roles roles) {
   return roleRepository.save(roles);

    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);

    }

    @Override
    public Roles findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Roles findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).get();
    }
}
