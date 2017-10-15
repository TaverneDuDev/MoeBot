package fr.tavernedudev.bot.moe;

import fr.tavernedudev.bot.moe.slack.SlackUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by sbeex on 14.10.17.
 */
@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot", "fr.tavernedudev.bot.moe"})
public class MoeBot {

    /**
     * Entry point of the application. Run this method to start the sample bots,
     * but don't forget to add the correct tokens in application.properties file.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MoeBot.class, args);
    }
}
