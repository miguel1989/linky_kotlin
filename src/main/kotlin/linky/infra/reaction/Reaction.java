package linky.infra.reaction;

import com.google.common.reflect.TypeToken;
import linky.infra.command.Command;
import linky.infra.command.Return;

public interface Reaction<C extends Command<R>, R extends Return> {
    R react(C command);

    default TypeToken<C> commandType() {
        return new TypeToken<C>(getClass()) {
        };
    }
}