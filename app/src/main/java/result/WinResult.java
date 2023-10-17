package result;

import enums.Color;

import java.util.Optional;

public class WinResult implements Result<Boolean, Color>{
    private final boolean winResult;
    private final Color color;

    public WinResult(boolean winResult, Color color) {
        this.winResult = winResult;
        this.color = color;
    }

    @Override
    public Boolean getKey() {
        return this.winResult;
    }

    @Override
    public Optional<Color> getValue() {
        return Optional.ofNullable(this.color);
    }
}
