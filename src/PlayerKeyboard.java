import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerKeyboard implements Player {
    private int last;
    private final KeyBinding keyBinding;
    PlayerKeyboard(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        last = e.getKeyCode();
                        return false;
                    }
                });
    }

    @Override
    public Move getMove() {
        final Move ret = keyBinding.moveForCode(last);
        last = 0;
        return ret;
    }
}
