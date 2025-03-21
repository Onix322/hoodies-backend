package com.hoodiesbackend.entities.product.helpers.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@NoArgsConstructor
public class QueryService {

    private final String template = "FROM Product p WHERE ";
    private final StringBuilder query = new StringBuilder();

    public QueryService addTemplate() {
        query.append(template);
        return this;
    }

    public QueryService addColumnName(String columnName) {
        query.append("p.").append(columnName);
        return this;
    }

    public QueryService addLike(Object value) {
        query.append(" LIKE '%").append(value).append("%'");
        return this;
    }

    public QueryService addEqual(Object value) {
        query.append("=").append(value);
        return this;
    }

    public QueryService addOpenParentheses() {
        query.append("(");
        return this;
    }

    public QueryService addCloseParentheses() {
        query.append(")");
        return this;
    }


    public QueryService addMatchOperator(SearchMatchingOperators matchingOperator, Object keyword) {
        switch (matchingOperator) {
            case LIKE -> this.addLike(keyword);
            case EQUALS -> this.addEqual(keyword);
        }

        return this;
    }

    public QueryService addOperator(SearchLogicalOperators operators) {
        query.append(" ").append(operators).append(" ");
        return this;
    }

    public QueryService queryFromList(List<Object> list, String columnName, SearchLogicalOperators searchLogicalOperator, SearchMatchingOperators searchMatchingOperator) {

        this.addOpenParentheses();

        for (int i = 0; i < list.size(); i++) {
            Object keyword = list.get(i);

            this.addColumnName(columnName)
                    .addMatchOperator(searchMatchingOperator, keyword);

            if (i != list.size() - 1) {
                this.addOperator(searchLogicalOperator);
            }
        }

        this.addCloseParentheses();
        return this;
    }

    public QueryService cleanQuery() {
        query.delete(0, query.length());
        return this;
    }

    public String buildQuery() {
        return query.toString().replace(")(", ") AND (")
                .replace(") (", ") AND (");
    }

    @Override
    public String toString() {
        return this.query.toString();
    }
}
