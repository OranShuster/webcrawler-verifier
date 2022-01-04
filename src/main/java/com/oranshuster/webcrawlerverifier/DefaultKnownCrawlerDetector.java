package com.oranshuster.webcrawlerverifier;

import com.google.common.collect.ImmutableList;
import com.oranshuster.webcrawlerverifier.bots.BotCheckerResult;
import com.oranshuster.webcrawlerverifier.bots.KnownHostBotVerifier;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 */
public class DefaultKnownCrawlerDetector implements KnownCrawlerDetector {
    @NotNull
    private final UserAgentAnalyzer uaa;

    @NotNull
    private final List<KnownHostBotVerifier> verifiers;

    /**
     * @param verifiers List of crawler verifiers
     * @param uaa Instance of UserAgentAnalyzer
     */
    public DefaultKnownCrawlerDetector(@NotNull List<KnownHostBotVerifier> verifiers, @NotNull UserAgentAnalyzer uaa) {
        this.verifiers = ImmutableList.copyOf(verifiers);
        this.uaa = uaa;
    }

    @NotNull
    @Override
    public Optional<KnownCrawlerResult> detect(String userAgent, @NotNull String ip) {
        for (KnownHostBotVerifier verifier : verifiers) {
            BotCheckerResult check = verifier.check(userAgent, ip, uaa);
            if (check != BotCheckerResult.IS_NOT) {
                return Optional.of(new KnownCrawlerResult(verifier.getIdentifier(), convert(check)));
            }
        }
        return Optional.empty();
    }

    protected KnownCrawlerResultStatus convert(BotCheckerResult check) {
        assert Objects.equals(4, BotCheckerResult.values().length);
        assert Objects.equals(3, KnownCrawlerResultStatus.values().length);

        switch (check) {
            case IS:
                return KnownCrawlerResultStatus.VERIFIED;
            case IMPERSONATOR:
                return KnownCrawlerResultStatus.IMPERSONATOR;
            case FAILED:
                return KnownCrawlerResultStatus.FAILED;
            case IS_NOT:
                throw new UnsupportedOperationException("Not convertible!");
            default:
                throw new UnsupportedOperationException(check.name());
        }
    }

}
