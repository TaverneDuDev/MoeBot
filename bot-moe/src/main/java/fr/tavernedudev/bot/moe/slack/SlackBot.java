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
import ch.sbeex.slack.api.model.channel.Channel;
import ch.sbeex.slack.api.model.channel.ChannelInfo;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Optional;
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

        if(event.getUserId() == null || event.getUserId().equals("U7K7XU5AS")){
            return;
        }

        String message = matcher.group(0);
        System.out.println("message = "+ message);
        Optional<Channel> debugChannel = getUtil().getChannelByName("debug");

        if(debugChannel.isPresent() && debugChannel.get().getId().equals(event.getChannelId())){


            ChannelInfo channelInfo = this.getUtil().getChannelInfo(event.getChannelId());

            if(channelInfo.getChannel().getLatest().isSubTopic()){
                return;
            }

            if(!isMessageContainsUrl(message)){

                UserInfo writerInfos = this.getUtil().getUserInfo(channelInfo.getChannel().getLatest().getUser());

                reply(session, event, new Message("Désolé " + writerInfos.getUser().getName() + " mais on ne poste que des liens ici"));
                this.getUtil().deleteMessage(event.getChannelId(), channelInfo.getChannel().getLatest().getTs());
            } else {
                System.out.println("GREAT");
                this.getUtil().addReaction("thumbsup", event.getChannelId(), channelInfo.getChannel().getLatest().getTs());
                this.getUtil().addReaction("thumbsdown", event.getChannelId(), channelInfo.getChannel().getLatest().getTs());
            }

        }

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

}