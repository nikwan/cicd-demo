package com.acoustic.gateway.routes;



import java.io.IOException;
import java.time.Duration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpServerErrorException;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class AcousticRoutesBuilder {
	
	final long TIMEOUT_DURATION_SECONDS = 10;
	
	private static final Logger log = LoggerFactory.getLogger(AcousticRoutesBuilder.class);
	
	//@Value("${config.oauth2.clientid}")
	private String clientid;

	//@Value("${config.oauth2.clientSecret}")
	private String clientSecret;
	
	private String basicAuth;
	
	@PostConstruct
	void init(){
		//basicAuth = "Basic " + Base64.getEncoder().encodeToString((clientid + ":" + clientSecret).getBytes());
		//log.debug("basicAuth:" + basicAuth);
	}
	
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		
		return builder.routes()
				
				 //acoustic-search
				.route("acoustic-search", r -> r.path("/search/**")
				.filters( f -> f.circuitBreaker( c -> c.setName("acoustic-search-cb").setFallbackUri("forward:/searchServiceFB")))
				.uri("lb://acoustic-search"))
				
				//acoustic-users
				.route("acoustic-users", r -> r.path("/users/**")
				.filters( f -> f.circuitBreaker( c -> c.setName("acoustic-users-cb").setFallbackUri("forward:/usersServiceFB")))
				.uri("lb://acoustic-users"))

				.build();
	}
	
	public CircuitBreakerConfig con() {
		return CircuitBreakerConfig.custom().slidingWindowSize(10) .minimumNumberOfCalls(3)
		  .automaticTransitionFromOpenToHalfOpenEnabled(true)
		  .permittedNumberOfCallsInHalfOpenState(3)
		  .slidingWindowType(SlidingWindowType.COUNT_BASED)
		  .failureRateThreshold(56.0F)
		  .waitDurationInOpenState(Duration.ofMillis(5000))
		  .recordExceptions(HttpServerErrorException.class, IOException.class, Throwable.class)
		  .build();
		
	}
	
	public Resilience4JCircuitBreakerConfiguration edastakahtCK(String id) {
		return new Resilience4JConfigBuilder(id)
		.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(TIMEOUT_DURATION_SECONDS)).build())
        .circuitBreakerConfig(con())
        .build();
	}
	
	
	  @Bean 
	  public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer(CircuitBreakerRegistry reg) { 
		  
		  //MeterRegistry meterRegistry = new SimpleMeterRegistry();
		  
		  return factory -> factory.configureDefault(id -> {
			  log.info("circuit_brakers:{}" + id);
			  log.info("reg:{}", reg.getDefaultConfig().getFailureRateThreshold());
			  //reg.find("edastakhat-service-circuit-breaker").get();
			  //log.info("reg:{}", reg.getAllCircuitBreakers().get().getCircuitBreakerConfig().getPermittedNumberOfCallsInHalfOpenState());
			  
			  
			  CircuitBreaker cb = reg.circuitBreaker(id,con());
			  log.info("cb_frt:{}", cb.getCircuitBreakerConfig().getFailureRateThreshold());
			  factory.configureCircuitBreakerRegistry(reg);
			  
				 
			  return edastakahtCK(id);
		  });
		  

	  }
}
