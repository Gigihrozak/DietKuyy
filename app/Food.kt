data class Food(
    val brand: Brand,
    val classification: String,
    val foodId: String,
    val name: String,
    val nutrients: Nutrients,
    val revisionId: String,
    val servings: List<Serving>,
    val tags: List<Tag>,
    val upcs: Upcs,
    val volume: Double
)