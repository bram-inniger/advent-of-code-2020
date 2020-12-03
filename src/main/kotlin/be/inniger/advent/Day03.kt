package be.inniger.advent

class Day03 {

    private val slope = Slope(3, 1)

    fun solveFirst(treesDescription: List<String>): Int {
        val forest = Forest.of(treesDescription)

        return generateSequence(Tree(0, 0)) { Tree(it.x + slope.right, it.y + slope.down) }
            .takeWhile { it.y < forest.height }
            .filter { forest.containsTree(it) }
            .count()
    }

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
