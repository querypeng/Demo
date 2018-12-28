package store.pengfeng.repository;

import org.apache.ibatis.annotations.Param;
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
    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    User findByName(@Param("name") String name);
}
