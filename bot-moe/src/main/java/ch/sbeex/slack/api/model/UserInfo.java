/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package ch.sbeex.slack.api.model;

import me.ramswaroop.jbot.core.slack.models.User;

/**
 * UserInfo
 * Represent userInfo DTO of slack API
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class UserInfo {
    //TODO: see if it is boolean
    private String ok;
    private User user;

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
