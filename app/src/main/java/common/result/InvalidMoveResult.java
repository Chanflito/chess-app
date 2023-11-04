package common.result;

import java.util.Optional;

public class InvalidMoveResult implements Result<Boolean,String>{
    private final boolean isInvalid;
    private final String message;


    public InvalidMoveResult(boolean isInvalid, String message) {
        this.isInvalid = isInvalid;
        this.message = message;
    }

    @Override
    public Boolean getKey() {
        return this.isInvalid;
    }

    @Override
    public Optional<String> getValue() {
        return Optional.ofNullable(this.message);
    }
}
