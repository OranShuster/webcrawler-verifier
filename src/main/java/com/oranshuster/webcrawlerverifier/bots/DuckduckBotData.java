package com.oranshuster.webcrawlerverifier.bots;

import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Resources:
 * https://duckduckgo.com/duckduckbot
 */
public class DuckduckBotData implements CrawlerData {

    private static final Predicate<String> PREDICATE = userAgent -> userAgent != null && userAgent.contains("DuckDuckBot");

    /**
     * As documented by duckduckgo: https://duckduckgo.com/duckduckbot
     */
    private static final ImmutableSet<String> IPS = ImmutableSet.of("72.94.249.34", "72.94.249.35", "72.94.249.36", "72.94.249.37", "72.94.249.38");


    private static final DuckduckBotData INSTANCE = new DuckduckBotData();

    public static DuckduckBotData getInstance() {
        return INSTANCE;
    }

    private DuckduckBotData() {
    }


    @NotNull
    @Override
    public String getIdentifier() {
        return "DUCKDUCKBOT";
    }

    @NotNull
    @Override
    public java.util.function.Predicate<String> getUserAgentChecker() {
        return PREDICATE;
    }

    @NotNull
    @Override
    public Set<String> getIps() {
        return IPS;
    }

    @NotNull
    @Override
    public Set<String> getHostnames() {
        return Collections.emptySet();
    }
}
