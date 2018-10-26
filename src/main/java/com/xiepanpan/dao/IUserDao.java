package com.xiepanpan.dao;

import com.xiepanpan.entity.User; /**
 * describe:
 *
 * @author xiepanpan
 * @date 2018/10/26
 */

public interface IUserDao {
    User findUser(User u);
}
