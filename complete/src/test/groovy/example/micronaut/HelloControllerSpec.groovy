package example.micronaut

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class HelloControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())


    void "test hello world response"() {
        when:
        HttpRequest request = HttpRequest.GET('/hello')
        String rsp  = client.toBlocking().retrieve(request)

        then:
        rsp == "Hello World"
    }
}
