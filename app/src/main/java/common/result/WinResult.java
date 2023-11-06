package common.result;

import common.enums.Color;

import java.util.Optional;

public class WinResult implements Result<Boolean, Color>{
    private final boolean winResult;
    private final Color color;

    public WinResult(boolean winResult, Color color) {
        this.winResult = winResult;
        this.color = color;
    }

    @Override
    public Boolean key() {
        return this.winResult;
    }

    @Override
    public Optional<Color> value() {
        return Optional.ofNullable(this.color);
    }
}
