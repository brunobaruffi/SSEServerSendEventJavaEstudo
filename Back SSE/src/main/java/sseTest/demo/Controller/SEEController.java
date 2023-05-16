package sseTest.demo.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class SEEController {
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping("/sse")
    public SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter(300_000L);// define a conexão e o time out no caso 300000ms
        emitters.add(emitter);

        // Remove o emitter quando a conexão é fechada
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));

        return emitter;
    }
    @GetMapping("/abc")
    public void test(){
        //envia mensagem para todos
        sendToAll("testestestes");
    }

    public void sendToAll(String data) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(data, MediaType.TEXT_PLAIN);
            } catch (IOException e) {
                // Trata exceção
            }
        }
    }
}
