package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Resources:
 * https://integralads.com/site-indexing-policy/
 * https://integralads.com/news/integral-ad-science-acquires-admantx/
 */
public class IntegralAdScienceBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getValue("DeviceName").contains("Integralads") || ua.getValue("DeviceName").contains("Admantx Robot");

    private static final IntegralAdScienceBotData INSTANCE = new IntegralAdScienceBotData();

    public static IntegralAdScienceBotData getInstance() {
        return INSTANCE;
    }

    private IntegralAdScienceBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "IASBOT";
    }

    @NotNull
    @Override
    public Predicate<UserAgent> getUserAgentChecker() {
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
        return Collections.emptySet();
    }
}
