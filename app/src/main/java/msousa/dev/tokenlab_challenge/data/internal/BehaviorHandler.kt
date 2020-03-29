package msousa.dev.tokenlab_challenge.data.internal

fun <T> runWithBehavior(block: BehaviorBuilder<T>.() -> Unit) {
    BehaviorBuilder<T>().apply(block).build()
}