

import org.example.adapterapp.adapter.dto.MessageServiceB;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Эмулятор сервиса B
 */
@RestController
@RequestMapping("/emul")
public class EmulatorController {

    @PostMapping
    public ResponseEntity<MessageServiceB> getMsg(@RequestBody MessageServiceB msgB) {
        System.out.println("****************");
        System.out.println(msgB);
        return ResponseEntity.ok(msgB);

    }

}
