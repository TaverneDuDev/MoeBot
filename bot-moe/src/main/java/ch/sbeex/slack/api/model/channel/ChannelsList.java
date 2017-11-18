/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package ch.sbeex.slack.api.model.channel;

import java.util.ArrayList;
import java.util.List;

/**
 * ChannelsList
 * List all channels - DTO of slack API
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class ChannelsList {

    private Boolean ok;
    private List<Channel> channels = new ArrayList<>();

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
