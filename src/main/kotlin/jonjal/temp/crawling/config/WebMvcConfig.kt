package jonjal.temp.crawling.config

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Playwright
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.CacheControl
import org.springframework.http.MediaType
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit


@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        if (!registry.hasMappingForPattern("/files/**")) {
            registry
                .addResourceHandler("/files/**")
                .addResourceLocations("file:" + "files/")
                .setCacheControl(CacheControl.maxAge(365L, TimeUnit.DAYS))
        }
    }

    @Bean
    fun jsonCustomizer(): Jackson2ObjectMapperBuilderCustomizer {

        val dateFormat = "yyyy-MM-dd"
        val dateTimeFormat = "yyyy-MM-dd HH:mm"
        return Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
            builder.simpleDateFormat(dateTimeFormat)
            builder.serializers(LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)))
            builder.deserializers(LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)))
            builder.serializers(LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
            builder.deserializers(
                LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat))
            )
        }
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean(destroyMethod = "close")
    fun browser(): BrowserContext {
        val browserContext = Playwright.create().chromium().launch(
            BrowserType.LaunchOptions()
                .setHeadless(false)
        ).newContext(
            //Browser.NewContextOptions() // 추가 브라우저 헤더 등 여러가지 옵션 지정이 가능하다.
        )
        return browserContext
    }


    @Bean
    fun restTemplate(): RestTemplate? {
        return RestTemplate(getClientHttpRequestFactory()!!).apply {
            val converters: MutableList<HttpMessageConverter<*>> = ArrayList()
            val converter = MappingJackson2HttpMessageConverter()
            converter.supportedMediaTypes = Collections.singletonList(MediaType.ALL)
            converters.add(converter)
            messageConverters = converters
        }
    }

    /**
     * 사용자 커넥션 타입아웃
     * @return
     */
    private fun getClientHttpRequestFactory(): SimpleClientHttpRequestFactory? {
        val clientHttpRequestFactory = SimpleClientHttpRequestFactory()
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(10000)

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(10000)
        return clientHttpRequestFactory
    }
}