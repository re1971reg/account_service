package faang.school.accountservice.config.context;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserHeaderFilter implements Filter {

    public static final String MISSING_X_USER_ID = "Missing required header 'x-user-id'. " +
        "Please include 'x-user-id' header with a valid user ID in your request.";
    private final UserContext userContext;
    private final Set<String> swaggerWords = Set.of("swagger", "api-docs");


    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.debug("request uri: {}", req.getRequestURI());
        String userId = req.getHeader("x-user-id");
        if (userId != null) {
            userContext.setUserId(Long.parseLong(userId));
        } else {
            if (!isSwaggerRequest(req)) {
                throw new IllegalArgumentException(MISSING_X_USER_ID);
            }
        }
        try {
            chain.doFilter(request, response);
        } finally {
            userContext.clear();
        }
    }

    private boolean isSwaggerRequest(HttpServletRequest req) {
        String path = req.getServletPath();
        return swaggerWords.stream()
            .anyMatch(path::contains);
    }
}
