package com.hoodiesbackend.entities.product.helpers.search;

import lombok.Getter;

public class ProductQuery {

    @Getter
    private final String template = "FROM Product p WHERE";
    private final StringBuilder query = new StringBuilder();

    public ProductQuery() {
        this.addTemplate();
    }

    private void addTemplate() {
        query.append(template);
    }

    public ProductQuery addColumnName(String columnName){
        query.append(" p.").append(columnName);
        return this;
    }

    public ProductQuery addLike(String value) {
        query.append(" LIKE '%").append(value).append("%'");
        return this;
    }

    public ProductQuery addEqual(Object value) {
        query.append("=").append(value);
        return this;
    }

    public ProductQuery addOperator(SearchLogicalOperators operators){
        query.append(" ").append(operators);
        return this;
    }

    public ProductQuery addOpenParentheses(){
        query.append(" (");
        return this;
    }

    public ProductQuery addCloseParentheses(){
        query.append(")");
        return this;
    }
    public StringBuilder getStringBuilder(){
        return query;
    }

    public String buildQuery(){
        return query.toString();
    }

    @Override
    public String toString(){
        return this.query.toString();
    }
}
