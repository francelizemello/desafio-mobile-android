package br.com.listagem.util

import java.util.concurrent.atomic.AtomicInteger


class SimpleCountingIdlingResource(resourceName: String?) :
    IdlingRescource {
    val name: String
    private val counter: AtomicInteger = AtomicInteger(0)

    // written from main thread, read from any thread.
    @Volatile
    private var resourceCallback: ResourceCallback? = null

    val isIdleNow: Boolean
        get() = counter.get() === 0

    override fun registerIdleTransitionCallback(resourceCallback: ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

    /**
     * Increments the count of in-flight transactions to the resource being monitored.
     */
    fun increment() {
        counter.getAndIncrement()
    }


    fun decrement() {
        val counterVal: Int = counter.decrementAndGet()
        if (counterVal == 0) {
            // we've gone from non-zero to zero. That means we're idle now! Tell espresso.
            if (null != resourceCallback) {
                resourceCallback.onTransitionToIdle()
            }
        }
        require(counterVal >= 0) { "Counter has been corrupted!" }
    }


    init {
        name = checkNotNull(resourceName)
    }
}
