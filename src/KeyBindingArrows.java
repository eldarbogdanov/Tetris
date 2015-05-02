import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyBindingArrows implements KeyBinding {

    private final Map<Integer, Move> binding;
    public KeyBindingArrows() {
        binding = new HashMap<Integer, Move>();
        binding.put(KeyEvent.VK_DOWN, new MoveDown());
        binding.put(KeyEvent.VK_UP, new MoveRotate());
        binding.put(KeyEvent.VK_LEFT, new MoveLeft());
        binding.put(KeyEvent.VK_RIGHT, new MoveRight());
    }

    @Override
    public Move moveForCode(int keyCode) {
        return binding.get(keyCode);
    }
}
