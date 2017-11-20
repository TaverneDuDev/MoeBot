/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package fr.tavernedudev.bot.moe.slack;

import ch.sbeex.slack.api.SlackClient;
import ch.sbeex.slack.api.SlackClientImpl;
import ch.sbeex.slack.api.model.UserInfo;
import ch.sbeex.slack.api.model.channel.ChannelInfo;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import fr.tavernedudev.bot.moe.utils.Nullable;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SlackBot class
 * Establish the behaviors of MoeBot
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
@Component
public class SlackBot extends Bot {

    private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);
    private static final String URL_REGEX = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";

    private SlackClient util;

    /**
     * Slack token from application.properties file. You can get your slack token
     * next <a href="https://my.slack.com/services/new/bot">creating a new bot</a>.
     */
    @Value("${slackBotToken}")
    private String slackToken;

    @Value("${slackAdminToken}")
    private String slackAdminToken;

    @Value("${botUserId}")
    private String botUserId;

    @Value("#{'${linkOnlyChannels}'.split(',')}")
    private List<String> linkOnlyChannels;

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        logger.info("Websocket connected");

        this.util = new SlackClientImpl(getSlackToken(), getSlackAdminToken());
    }

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    public String getSlackAdminToken(){
        return slackAdminToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    private SlackClient getUtil(){
        return this.util;
    }

    /**
     * Invoked when bot receives an event of type message with text satisfying the given pattern
     *
     * @param session
     * @param event
     */
    @Controller(events = EventType.MESSAGE, pattern = "^(.*)$")
    public void onReceiveMessage(WebSocketSession session, Event event, Matcher matcher) {

        if(!shouldDoSomething(event)){
            return;
        }

        String message = matcher.group(0);
        ChannelInfo channelInfo = this.getUtil().getChannelInfo(event.getChannelId());
        if(!isMessageContainsUrl(message)){
            UserInfo writerInfos = this.getUtil().getUserInfo(channelInfo.getChannel().getLatest().getUser());
            reply(session, event, new Message("Désolé " + writerInfos.getUser().getName() + " mais on ne poste que des liens ici"));
            this.getUtil().deleteMessage(event.getChannelId(), channelInfo.getChannel().getLatest().getTs());
        } else {
            this.getUtil().addReaction("thumbsup", event.getChannelId(), channelInfo.getChannel().getLatest().getTs());
            this.getUtil().addReaction("thumbsdown", event.getChannelId(), channelInfo.getChannel().getLatest().getTs());
        }
    }

    private boolean shouldDoSomething(@Nullable Event event) {
        return event != null && event.getUserId()!=null && !isBot(event.getUserId()) && isALinkOnlyChannel(event.getChannelId()) && !isLastPostInChannelASubtopicMessage(event.getChannelId());
    }

    @VisibleForTesting
    boolean isBot(@Nullable String userId) {
        return userId != null && userId.equals(getBotUserId());
    }

    @VisibleForTesting
    boolean isALinkOnlyChannel(@Nullable String channelId){
        if(StringUtils.isBlank(channelId)){
            return false;
        }

        ChannelInfo channelInfo = this.getUtil().getChannelInfo(channelId);

        if(channelInfo.getChannel()==null || StringUtils.isBlank(channelInfo.getChannel().getName())){
            return false;
        }

        String channelName = channelInfo.getChannel().getName();
        return linkOnlyChannels.contains(channelName);
    }

    private boolean isLastPostInChannelASubtopicMessage(String channelId){
        String errorMessage = "Cannot determine if the last post is a subtopic because channelId is null";
        Preconditions.checkNotNull(channelId, errorMessage);

        ChannelInfo channelInfo = this.getUtil().getChannelInfo(channelId);
        return channelInfo.getChannel().getLatest().isSubTopic();
    }

    /**
     * Returns true when the given message contains a plain full valid url
     * @param message the message to evaluate
     * @return true when url is found
     */
    private boolean isMessageContainsUrl(String message) {
        Pattern p = Pattern.compile(URL_REGEX);
        Matcher m = p.matcher(message);//replace with string to compare
        return m.find();
    }

    public String getBotUserId(){
        return this.botUserId;
    }

}