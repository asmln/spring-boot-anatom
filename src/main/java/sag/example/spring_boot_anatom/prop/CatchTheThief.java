package sag.example.spring_boot_anatom.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class CatchTheThief {
    @Value("${court.testimony}") private String testimony;

    public void readOutTestimony() {
        IO.println("   \uD83E\uDDD1\u200D⚖\uFE0F\uD83D\uDCE2[properties] " + testimony);
    }
}
