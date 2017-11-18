/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package ch.sbeex.slack.api;

import ch.sbeex.slack.api.model.UserInfo;
import ch.sbeex.slack.api.model.channel.Channel;
import ch.sbeex.slack.api.model.channel.ChannelInfo;

import java.util.List;
import java.util.Optional;

/**
 * Interface to communicate with slack API
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public interface SlackClient {

    /**
     * Get informations about a user with a given id
     * @param userId id of the channel
     * @return UserInfo
     */
    UserInfo getUserInfo(String userId);

    /**
     * Get informations about a channel with a given id
     * @param channelId id of the channel
     * @return ChannelInfo
     */
    ChannelInfo getChannelInfo(String channelId);

    /**
     * Add reaction (emoji) to a message
     * @param emoji reaction to send (ie: cry)
     * @param channelId id of the channel
     * @param timestamp timestamp of the message on which the emoji should be added
     */
    void addReaction(String emoji, String channelId, String timestamp);

    /**
     * Delete a message
     * @param channelId id of the channel
     * @param timestamp timestamp of the message to delete
     *
     * Note: require to be authenticated as an admin of the slack group. (Use developer api token)
     * TODO: document it more efficiently
     */
    void deleteMessage(String channelId, String timestamp);

    /**
     * Get the list of available channels
     * @return list of channels
     */
    List<Channel> getChannels();

    /**
     * Get a channel by name
     * @param name of the channel to find
     * @return the channel
     */
    Optional<Channel> getChannelByName(String name);
}
