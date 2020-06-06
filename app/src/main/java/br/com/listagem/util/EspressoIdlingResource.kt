package br.com.listagem.util



object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val mCountingIdlingResource: SimpleCountingIdlingResource =
        SimpleCountingIdlingResource(RESOURCE)

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

    val idlingResource: IdlingResource
        get() = mCountingIdlingResource
}