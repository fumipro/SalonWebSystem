package jp.co.salonwebsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
    .permitAll()
).logout(logout -> logout
    .logoutUrl("/logout")
    .logoutSuccessUrl("/")
)
          .authorizeHttpRequests(authorize -> authorize
          .requestMatchers("/").permitAll()
          .requestMatchers("/concept").permitAll()
          .requestMatchers("/access").permitAll()
          .requestMatchers("/reservation").permitAll()
          .requestMatchers("/reservation_confirm").permitAll()
          .requestMatchers("/reservation_finish").permitAll()
          .requestMatchers("/contact").permitAll()
          .requestMatchers("/contact_confirm").permitAll()
          .requestMatchers("/contact_finish").permitAll()
          .requestMatchers("/login").permitAll()

        //   .requestMatchers("/admin").permitAll()
        //   .requestMatchers("/admin/check_contact").permitAll()
        //   .requestMatchers("/admin/check_reservation").permitAll()
          .requestMatchers("/img/**", "/css/**").permitAll()
          .anyRequest().authenticated()
    )
          
          

       ;
    return http.build();
  }

}