package MoneyCatcher.HobHub.Flask;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestSendToFlaskDto {
    private final String prev;
    private final String motive;
}
