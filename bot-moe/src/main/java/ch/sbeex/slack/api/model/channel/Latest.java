/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package ch.sbeex.slack.api.model.channel;


/**
 * Latest
 * dto received from slack API
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class Latest {

    private String type;
    private String user;
    private String text;
    private String threadTs;
    private String parentUserId;
    private String ts;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getThreadTs() {
        return threadTs;
    }

    public boolean isSubTopic(){
        return getThreadTs() != null;
    }

    public void setThreadTs(String threadTs) {
        this.threadTs = threadTs;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    @Override
    public String toString() {
        return "Latest{" +
                "type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", text='" + text + '\'' +
                ", threadTs='" + threadTs + '\'' +
                ", parentUserId='" + parentUserId + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
