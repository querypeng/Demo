package store.pengfeng.repository;

import org.springframework.data.repository.CrudRepository;
import store.pengfeng.domain.User;

/**
 * <pre>
 * Description
 * Copyright:	Copyright (c)2018
 * Author:		shishi
 * Created at:	2018/12/27 16:12
 * </pre>
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
}
