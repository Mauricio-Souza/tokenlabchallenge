package msousa.dev.tokenlab_challenge.presentation.extensions

fun String.formattedDate() : String {
    return if (this.isEmpty()) ""
    else {
        val splitDate = this.split("-")
        return "${splitDate[2]}/${splitDate[1]}/${splitDate[0]}"
    }
}