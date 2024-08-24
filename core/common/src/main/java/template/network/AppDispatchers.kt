package template.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

enum class AppDispatchers {
    IO,
    DEFAULT,
}

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatcher: AppDispatchers)
