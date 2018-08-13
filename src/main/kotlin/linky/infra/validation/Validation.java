package linky.infra.validation;

import com.google.common.reflect.TypeToken;
import linky.infra.command.Command;

public interface Validation<C extends Command> {
    void validate(C command);

    default TypeToken<C> commandType() {
        return new TypeToken<C>(getClass()) {
        };
    }
}
