package setup;

import com.github.tomakehurst.wiremock.WireMockServer;
import data.Constants;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockSetup {
    private WireMockServer wireMockServer;

    public void startServer() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        configureFor("localhost", 8080);
    }

    public void stopServer() {
        if (wireMockServer != null) wireMockServer.stop();
    }

    public void stubGetUsers200() {
        stubFor(get(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(Constants.USERS_RESPONSE)));
    }

    public void stubGetUsers500() {
        stubFor(get(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(500)));
    }
}
