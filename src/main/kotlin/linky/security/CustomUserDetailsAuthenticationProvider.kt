package linky.security

import linky.user.UserDao
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsAuthenticationProvider(
        private val userDao: UserDao,
        private val passwordEncoder: PasswordEncoder
) : AbstractUserDetailsAuthenticationProvider() {
    override fun additionalAuthenticationChecks(userDetails: UserDetails?, authentication: UsernamePasswordAuthenticationToken?) {
        if (authentication?.credentials == null) {
            throw BadCredentialsException("no password")
        }
        val rawPass: String = authentication.credentials.toString()
        if (!passwordEncoder.matches(rawPass, userDetails?.password)) {
            throw BadCredentialsException("invalid password")
        }
    }

    override fun retrieveUser(email: String, authentication: UsernamePasswordAuthenticationToken?): UserDetails {
        return userDao.findByEmail(email) ?: throw UsernameNotFoundException("no user was found")
    }
}