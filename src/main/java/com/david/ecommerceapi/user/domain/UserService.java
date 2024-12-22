package com.david.ecommerceapi.user.domain;

import java.util.List;

public interface UserService {

    User update(User user);

    List<User> findAll();

    User findById(Long id);

    void delete(Long id);

}
