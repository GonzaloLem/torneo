package com.api.torneo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests ->

                // Configuración de rutas y permisos
                requests.requestMatchers("/**").permitAll()
                // Permitir GET en todas las rutas
                .requestMatchers(HttpMethod.GET, "/**").permitAll()

                // Restringe los demás métodos a usuarios autenticados
                .requestMatchers(HttpMethod.POST, "/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/**").authenticated()


        );

        // Deshabilitar CSRF si no es necesario
        //http.csrf(AbstractHttpConfigurer::disable);
        // Deshabilitar el formulario de inicio de sesión
        http.formLogin(AbstractHttpConfigurer::disable);
        //deshabilitar HTTP Basic si no se utiliza
        http.httpBasic(AbstractHttpConfigurer::disable);



        return http.build();
    }
}
