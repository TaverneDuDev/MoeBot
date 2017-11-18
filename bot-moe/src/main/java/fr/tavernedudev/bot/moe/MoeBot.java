/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package fr.tavernedudev.bot.moe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MoeBot main class
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
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
