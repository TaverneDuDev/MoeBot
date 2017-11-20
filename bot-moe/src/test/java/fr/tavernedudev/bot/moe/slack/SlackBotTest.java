/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package fr.tavernedudev.bot.moe.slack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SlackBotTest {

    private SlackBot slackbot;

    @Before
    public void tearUp(){
        this.slackbot = spy(new SlackBot());
    }

    @Test
    public void isBotShouldReturnFalseWhenNullParameterIsGiven() throws Exception {

        // Given
        String nullParameter = null;

        // When
        boolean actualResponse = slackbot.isBot(nullParameter);

        // Then
        assertFalse(actualResponse);

    }

    @Test
    public void isBotShouldReturnTrueWhenTheBotUserIdIsGiven() throws Exception {

        // Given
        String correctBotId = "339955";
        when(slackbot.getBotUserId()).thenReturn(correctBotId);

        // When
        boolean actualResponse = slackbot.isBot(correctBotId);

        // Then
        assertTrue(actualResponse);

    }

    @Test
    public void isBotShouldReturnFalseWhenTheGivenUserIdIsNotTheSameAsTheBotIsGiven() throws Exception {

        // Given
        String correctBotId = "339955";
        when(slackbot.getBotUserId()).thenReturn(correctBotId);

        String incorrectBotId = "somethingwrong";
        // When
        boolean actualResponse = slackbot.isBot(incorrectBotId);

        // Then
        assertFalse(actualResponse);

    }

    @Test
    public void isALinkChannelShouldReturnFalseWhenNullIsGiven() throws Exception {

        // Given
        String nullChannelId = null;

        // When
        boolean actualResponse = slackbot.isALinkOnlyChannel(nullChannelId);

        // Then
        assertFalse(actualResponse);

    }



}