package be.inniger.advent

class Day21 {

    fun solveFirst(foodsRaw: List<String>): Int {
        val foods = foodsRaw.map { Food.of(it) }
        val allAllergens = foods.flatMap { it.allergens }.toSet()
        val allIngredients = foods.flatMap { it.ingredients }.toSet()

        val allergenToIngredients = allAllergens.map { allergen ->
            allergen to foods
                .filter { food -> food.allergens.contains(allergen) }
                .map { food -> food.ingredients }
                .reduce(Set<String>::intersect)
        }.toMap()

        val unsafeIngredients = allergenToIngredients.values.flatten().toSet()
        val safeIngredients = allIngredients - unsafeIngredients

        return safeIngredients
            .map { ingredient ->
                foods.filter { food -> food.ingredients.contains(ingredient) }.count()
            }
            .sum()
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
