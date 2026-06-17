package com.soulnard.commons.openapi;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * Traduce automáticamente las descripciones de la documentación OpenAPI
 * usando claves i18n desde messages.properties.
 */
@Configuration
public class OpenApiI18nCustomizer {

    @Bean
    public OpenApiCustomizer openApiI18nCustomizer(I18nUtil i18nUtil) {
        return openApi -> {
            if (openApi.getPaths() == null) return;

            for (Map.Entry<String, PathItem> pathEntry : openApi.getPaths().entrySet()) {
                PathItem pathItem = pathEntry.getValue();
                translateOperations(i18nUtil, pathItem.readOperations());
            }
        };
    }

    private void translateOperations(I18nUtil i18nUtil, List<Operation> operations) {
        if (operations == null) return;

        for (Operation operation : operations) {
            translateOperation(i18nUtil, operation);
        }
    }

    private void translateOperation(I18nUtil i18nUtil, Operation operation) {
        if (operation.getSummary() != null) {
            operation.setSummary(i18nUtil.getMessageOrDefault(
                    operation.getSummary(), operation.getSummary()));
        }
        if (operation.getDescription() != null) {
            operation.setDescription(i18nUtil.getMessageOrDefault(
                    operation.getDescription(), operation.getDescription()));
        }

        translateParameters(i18nUtil, operation.getParameters());
        translateResponses(i18nUtil, operation.getResponses());
    }

    private void translateParameters(I18nUtil i18nUtil, List<Parameter> parameters) {
        if (parameters == null) return;

        for (Parameter param : parameters) {
            if (param.getDescription() != null) {
                param.setDescription(i18nUtil.getMessageOrDefault(
                        param.getDescription(), param.getDescription()));
            }
        }
    }

    private void translateResponses(I18nUtil i18nUtil, ApiResponses responses) {
        if (responses == null) return;

        for (Map.Entry<String, ApiResponse> entry : responses.entrySet()) {
            ApiResponse response = entry.getValue();
            if (response.getDescription() != null) {
                response.setDescription(i18nUtil.getMessageOrDefault(
                        response.getDescription(), response.getDescription()));
            }
        }
    }
}
