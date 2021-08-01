package com.oranshuster.webcrawlerverifier;

import com.oranshuster.webcrawlerverifier.bots.KnownHostBotVerifier;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 *
 */
public interface KnownCrawlerDetector {

    /**
     * @param userAgent see {@link KnownHostBotVerifier#check(String, String)}
     * @param ip see {@link KnownHostBotVerifier#check(String, String)}
     * @return absent if none detected.
     */
    @NotNull
    Optional<KnownCrawlerResult> detect(String userAgent, @NotNull String ip);

}
