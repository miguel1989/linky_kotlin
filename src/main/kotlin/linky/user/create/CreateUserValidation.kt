package linky.user.create

import linky.infra.ValidationFailed
import linky.infra.validation.Validation
import linky.user.UserDao
import org.springframework.stereotype.Component

@Component
class CreateUserValidation(
        private val userDao: UserDao
) : Validation<CreateUserCommand> {
    override fun validate(command: CreateUserCommand) {
        //todo email validation
        val user = userDao.findByEmail(command.email)
        if (user != null) {
            throw ValidationFailed("email is already taken")
        }
    }
}