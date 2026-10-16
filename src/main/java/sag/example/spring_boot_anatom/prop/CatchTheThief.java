package sag.example.spring_boot_anatom.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sag.example.spring_boot_anatom.util.Anatom;
import sag.example.spring_boot_anatom.util.Slice;

@Component
public class CatchTheThief {
    @Value("${court.testimony}") private String testimony;

    public void readOutTestimony() {
        Anatom.out(Slice.DETECTIVE,"   \uD83E\uDDD1\u200D⚖\uFE0F\uD83D\uDCE2[properties] " + testimony);
    }
}
