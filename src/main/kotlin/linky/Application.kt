package linky

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class Application {
    @Bean
    fun init() = CommandLineRunner { // repository: CustomerRepository
//        repository.save(Customer("Jack", "Bauer"))
//        repository.save(Customer("Chloe", "O'Brian"))
//        repository.save(Customer("Kim", "Bauer"))
//        repository.save(Customer("David", "Palmer"))
//        repository.save(Customer("Michelle", "Dessler"))
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}