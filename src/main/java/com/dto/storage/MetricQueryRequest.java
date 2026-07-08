package com.dto.storage;

public class MetricQueryRequest {
    private String suite;
    private String metricName;
    private String aggregation;
    private Integer rankLimit;

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    public Integer getRankLimit() {
        return rankLimit;
    }

    public void setRankLimit(Integer rankLimit) {
        this.rankLimit = rankLimit;
    }
}
