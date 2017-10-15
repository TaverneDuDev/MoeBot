package fr.tavernedudev.bot.moe.slack.model;


import me.ramswaroop.jbot.core.slack.models.User;

public class UserInfo {

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
