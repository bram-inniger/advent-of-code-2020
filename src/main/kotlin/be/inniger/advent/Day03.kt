package be.inniger.advent

object Day03 {

    fun solveFirst(treesDescription: List<String>): Long {
        val forest = Forest.of(treesDescription)
        val slope = Slope(3, 1)

        return countEncounteredTrees(forest, slope)
    }

    fun solveSecond(treesDescription: List<String>): Long {
        val forest = Forest.of(treesDescription)
        val slopes = listOf(
            Slope(1, 1),
            Slope(3, 1),
            Slope(5, 1),
            Slope(7, 1),
            Slope(1, 2)
        )

        return slopes
            .map { countEncounteredTrees(forest, it) }
            .reduce(Long::times)
    }

    private fun countEncounteredTrees(forest: Forest, slope: Slope) =
        generateSequence(Tree(0, 0)) { Tree(it.x + slope.right, it.y + slope.down) }
            .takeWhile { it.y < forest.height }
            .filter { forest.containsTree(it) }
            .count()
            .toLong()

    private data class Slope(val right: Int, val down: Int)

    private data class Tree(val x: Int, val y: Int)

    private data class Forest(val trees: Set<Tree>, val width: Int, val height: Int) {

        companion object {
            internal fun of(treesDescription: List<String>): Forest {
                val width = treesDescription[0].length
                val height = treesDescription.size

                val trees = (0 until width)
                    .flatMap { x ->
                        (0 until height)
                            .map { y -> x to y }
                    }
                    .filter { treesDescription[it.second][it.first] == '#' }
                    .map { Tree(it.first, it.second) }
                    .toSet()

                return Forest(trees, width, height)
            }
        }

        fun containsTree(tree: Tree) = trees.contains(Tree(tree.x % width, tree.y))
    }
}
