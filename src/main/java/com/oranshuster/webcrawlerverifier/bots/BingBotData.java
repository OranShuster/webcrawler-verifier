package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

/**
 * Resources:
 * http://en.wikipedia.org/wiki/Bingbot
 * http://www.bing.com/webmaster/help/how-to-verify-bingbot-3905dc26
 * They also encourage the reverse and forward DNS lookup verification.
 * Ending with search.msn.com
 * <p>
 * This detects the Bingbot, plus the former msnbot.
 */
public class BingBotData implements CrawlerData {

    private static final java.util.function.Predicate<String> PREDICATE = userAgent -> {
        //see http://en.wikipedia.org/wiki/Bingbot

        //see http://en.wikipedia.org/wiki/Msnbot
        //this was the previous bot.
        //"As of February 2016 msnbot is still active ... (it was announced to retire "soon" quote some time ago...)"
        return (userAgent != null && (userAgent.contains("bingbot") || userAgent.contains("msnbot")));
    };

    private static final ImmutableSet<String> HOSTNAMES = ImmutableSet.of("search.msn.com");


    private static final BingBotData INSTANCE = new BingBotData();

    public static BingBotData getInstance() {
        return INSTANCE;
    }

    private BingBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "BINGBOT";
    }

    @NotNull
    @Override
    public java.util.function.Predicate<String> getUserAgentChecker() {
        return PREDICATE;
    }

    @NotNull
    @Override
    public Set<String> getIps() {
        return Collections.emptySet();
    }

    @NotNull
    @Override
    public Set<String> getHostnames() {
        return HOSTNAMES;
    }
}
