/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package ch.sbeex.slack.api.model.channel;

/**
 * ChannelInfo
 * Response from slack API
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class ChannelInfo {

    private Boolean ok;
    private Channel channel;


    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "ChannelInfo{" +
                "ok=" + ok +
                ", channel=" + channel +
                '}';
    }
}
