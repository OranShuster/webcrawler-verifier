package com.oranshuster.webcrawlerverifier.bots;

import nl.basjes.parse.useragent.UserAgent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QwantBotData implements CrawlerData {

    private static final Predicate<UserAgent> PREDICATE = ua -> ua.getUserAgentString().toLowerCase(Locale.ENGLISH).contains("qwant-news");

    private static final Set<String> IPS = IntStream.rangeClosed(0, 255).boxed()
            .map(String::valueOf)
            .map(i -> "194.187.171." + i)
            .collect(Collectors.toSet());

    private static final QwantBotData INSTANCE = new QwantBotData();

    public static QwantBotData getInstance() {
        return INSTANCE;
    }

    private QwantBotData() {
    }

    @NotNull
    @Override
    public String getIdentifier() {
        return "QWANTBOT";
    }

    @NotNull
    @Override
    public Predicate<UserAgent> getUserAgentChecker() {
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