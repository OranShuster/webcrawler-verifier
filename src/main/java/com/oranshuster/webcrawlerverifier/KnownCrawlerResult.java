package com.oranshuster.webcrawlerverifier;

import com.oranshuster.webcrawlerverifier.bots.CrawlerData;
import org.jetbrains.annotations.NotNull;

/**
 * Result returned by the {@link KnownCrawlerDetector#detect} method.
 */
public class KnownCrawlerResult {

    @NotNull
    private final String identifier;
    @NotNull
    private final KnownCrawlerResultStatus status;

    /**
     * @param identifier Bot identifier
     * @param status Verify status (result)
     */
    public KnownCrawlerResult(@NotNull String identifier, @NotNull KnownCrawlerResultStatus status) {
        this.identifier = identifier;
        this.status = status;
    }

    /**
     * @see CrawlerData#getIdentifier()
     * @return result identifier
     */
    @NotNull
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @return Verifier status (result)
     */
    @NotNull
    public KnownCrawlerResultStatus getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "KnownCrawlerResult{" +
                "identifier='" + identifier + '\'' +
                ", status=" + status +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnownCrawlerResult result = (KnownCrawlerResult) o;

        if (status != result.status) return false;
        return identifier.equals(result.identifier);
    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

}
