package fr.tavernedudev.bot.moe.slack;

import com.google.gson.Gson;
import fr.tavernedudev.bot.moe.slack.model.UserInfo;
import me.ramswaroop.jbot.core.slack.SlackService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbeex on 14.10.17.
 */
public class SlackUtil {

    // https://slack.com/api/users.info?token=xoxp-70601762133-70594891168-71807316609-884246b908&user=U22HGS74Y&pretty=1

    private String token;
    private List<Object> users;

    private OkHttpClient client;

    public SlackUtil(String token){
        this.users = new ArrayList<>();
        this.token = token;
        client = new OkHttpClient();
    }

    public UserInfo getUserInfo(String userId) {

        Request request = new Request.Builder().url("https://slack.com/api/users.info?token=" + this.token + "&user=" + userId).build();

        try {
            Response response = client.newCall(request).execute();
            String content = response.body().string();
            System.out.println(content);
            Gson gson = new Gson();

            UserInfo userInfo = gson.fromJson(content, UserInfo.class);
            System.out.println(userInfo.getUser().getName());
            return userInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }



}
