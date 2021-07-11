package be.inniger.advent

object Day21 {

    fun solveFirst(foodsRaw: List<String>): Int {
        val foods = foodsRaw.map { Food.of(it) }
        val mappedAllergens = mapAllergens(foods)

        val allIngredients = foods.flatMap { it.ingredients }.toSet()
        val unsafeIngredients = mappedAllergens.values.flatten().toSet()
        val safeIngredients = allIngredients - unsafeIngredients

        return safeIngredients.sumOf { ingredient ->
            foods.filter { food -> food.ingredients.contains(ingredient) }.count()
        }
    }

    fun solveSecond(foods: List<String>) =
        mapUniqueIngredient(
            mapAllergens(foods.map { Food.of(it) })
        )
            .entries
            .sortedBy { it.value }
            .joinToString(",") { it.key }

    private fun mapAllergens(foods: List<Food>) =
        foods.flatMap { it.allergens }
            .distinct()
            .associateWith { allergen ->
                foods
                    .filter { food -> food.allergens.contains(allergen) }
                    .map { food -> food.ingredients }
                    .reduce(Set<String>::intersect)
            }

    private tailrec fun mapUniqueIngredient(
        mappedAllergens: Map<String, Set<String>>,
        mappedIngredients: Map<String, String> = mapOf()
    ): Map<String, String> =
        if (mappedAllergens.isEmpty()) mappedIngredients
        else {
            val (allergen, ingredient) = mappedAllergens.entries.first { it.value.size == 1 }

            mapUniqueIngredient(
                mappedAllergens.filterKeys { it != allergen }.mapValues { it.value - ingredient },
                mappedIngredients + (ingredient.single() to allergen)
            )
        }

    private data class Food(val ingredients: Set<String>, val allergens: Set<String>) {

        companion object {
            private val regex = """^([\w ]+) \(contains ([\w, ]+)\)$""".toRegex()

            fun of(food: String) =
                if (regex.matches(food)) {
                    val (ingredients, allergens) = regex.find(food)!!.destructured
                    Food(ingredients.split(" ").toSet(), allergens.split(", ").toSet())
                } else Food(food.split(" ").toSet(), setOf())
        }
    }
}
