/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package ch.sbeex.slack.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ch.sbeex.slack.api.model.channel.Channel;
import ch.sbeex.slack.api.model.channel.ChannelInfo;
import ch.sbeex.slack.api.model.UserInfo;
import ch.sbeex.slack.api.model.channel.ChannelsList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * SlackClientImpl
 * Utility class to simplify communication with slack API
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class SlackClientImpl implements SlackClient {

    private String token;
    private String adminToken;
    private List<Channel> channels;

    private OkHttpClient client;

    public SlackClientImpl(String token, String adminToken){
        this.token = token;
        this.adminToken = adminToken;
        client = new OkHttpClient();

        this.channels = this.getChannels();

    }

    @Override
    public UserInfo getUserInfo(String userId) {

        Request request = new Request.Builder().url("https://slack.com/api/users.info?token=" + this.token + "&user=" + userId).build();

        try {
            Response response = client.newCall(request).execute();
            String content = response.body().string();
            Gson gson = new Gson();

            UserInfo userInfo = gson.fromJson(content, UserInfo.class);

            return userInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public ChannelInfo getChannelInfo(String channelId) {

        Request request = new Request.Builder().url("https://slack.com/api/channels.info?token=" + this.token + "&channel=" + channelId).build();
        try {
            Response response = client.newCall(request).execute();
            String jsonContent = response.body().string();
            System.out.println("JSON: "+jsonContent);

            final Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();


            ChannelInfo channelInfo = gson.fromJson(jsonContent, ChannelInfo.class);
            System.out.println("UNSERIALIZED: "+channelInfo.toString());

            return channelInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addReaction(String emoji, String channelId, String timestamp){
        Request request = new Request.Builder().url("https://slack.com/api/reactions.add?token=" + this.token + "&name=" + emoji + "&channel=" + channelId + "&timestamp=" + timestamp).build();
        try {
            Response response = client.newCall(request).execute();
            String content = response.body().string();
            System.out.println("Added reaction:");
            System.out.println(content);

        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void deleteMessage(String channelId, String timestamp){
        String url = "https://slack.com/api/chat.delete?token=" + this.adminToken + "&channel=" + channelId + "&ts=" + timestamp + "&as_user=true";
        System.out.println(url);
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String content = response.body().string();
            System.out.println(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Channel> getChannels() {

        String url = "https://slack.com/api/channels.list?token=" + this.adminToken;
        Request request = new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(request).execute();
            String content = response.body().string();
            Gson gson = new Gson();

            ChannelsList channels = gson.fromJson(content, ChannelsList.class);
            System.out.println(channels.getChannels().size());
            return channels.getChannels();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Optional<Channel> getChannelByName(String name) {
        for(Channel c : this.channels){
            if(c.getName().equals(name)){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

}
