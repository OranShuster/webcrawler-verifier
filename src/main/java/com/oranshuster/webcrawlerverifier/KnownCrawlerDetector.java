package com.oranshuster.webcrawlerverifier;

import com.oranshuster.webcrawlerverifier.bots.KnownHostBotVerifier;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 *
 */
public interface KnownCrawlerDetector {

    /**
     * @param userAgent see {@link KnownHostBotVerifier#check(String, String, UserAgentAnalyzer)}
     * @param ip        see {@link KnownHostBotVerifier#check(String, String, UserAgentAnalyzer)}
     * @return absent if none detected.
     */
    @NotNull
    Optional<KnownCrawlerResult> detect(String userAgent, @NotNull String ip);

}
