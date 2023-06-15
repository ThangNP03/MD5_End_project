package ra.service;

import ra.entity.user.RoleName;
import ra.entity.user.Roles;

public interface IRoleService extends IGenericService<Roles, Long >{
    Roles findByRoleName(RoleName roleName);
}
