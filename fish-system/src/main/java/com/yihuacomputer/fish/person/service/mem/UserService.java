package com.yihuacomputer.fish.person.service.mem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.person.entity.User;
import com.yihuacomputer.fish.person.service.base.DomainUserService;

@Service
public class UserService extends DomainUserService {

    private final BaseMemoryService memService = new BaseMemoryService();

    private final List<User> entities = new ArrayList<User>();

    @Override
    public User add(String code, IPerson user) {
        User result = make();
        result.setId(memService.nextId());
        result.setCode(code);
        result.setPerson(user);
        return add(result);
    }

    @Override
    public User add(IUser user) {
        User entity = memService.interface2Entity(user);
        entity.setId(memService.nextId());
        entities.add(entity);
        return entity;
    }

    @Override
    public User get(long id) {
        for (User user : entities) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NotFoundException(String.format(
                "not found entity [user.id = %s]", id));
    }

    @Override
    public User get(String code) {
        for (User user : entities) {
            if (code.equals(user.getCode())) {
                return user;
            }
        }
        throw new NotFoundException("user not found");
    }

    @Override
    public User getByPerson(String id) {
        for (User User : entities) {
            if (id.equals(User.getPerson().getGuid())) {
                return User;
            }
        }
        throw new NotFoundException("User not found");
    }

    @Override
    public Iterable<IUser> list() {
        return EntityUtils.<IUser> convert(entities);
    }

    @Override
    public Iterable<IUser> list(IFilter filter) {
        List<IUser> result = new ArrayList<IUser>();
        for (User each : entities) {
            if (memService.isMacth(each, filter)) {
                result.add(each);
            }
        }
        return result;
    }

    @Override
    public IPageResult<IUser> page(int offset, int limit, IFilter filter) {
        List<IUser> lists = new ArrayList<IUser>();
        EntityUtils.convert(list(filter), lists);
        return new PageResult<IUser>(lists, offset, limit);
    }

    @Override
    public void remove(User user) {
        entities.remove(user);
    }

    @Override
    public void remove(String code) {
        remove(get(code));
    }

    @Override
    public void remove(long id) {
        remove(get(id));

    }

    @Override
    public void update(IUser user) {

    }

    @Override
    public User convert(IUser user) {
        return memService.interface2Entity(user);
    }

	@Override
	public IPageResult<IUser> page(int offset, int limit, IFilter filter,
			String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUser login(String userCode, String plainText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExist(String personGuid) {
		// TODO Auto-generated method stub
		return false;
	}

}
