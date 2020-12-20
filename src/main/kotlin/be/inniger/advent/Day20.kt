package be.inniger.advent

import be.inniger.advent.util.head
import be.inniger.advent.util.sqrt
import be.inniger.advent.util.tail

class Day20 {

    private companion object {
        private val monster = parseMonster(
            """
                                  # 
                #    ##    ##    ###
                 #  #  #  #  #  #   
            """.trimIndent().split("\n")
        )

        private fun parseMonster(monster: List<String>) = Monster(
            monster.first().length,
            monster.size,
            monster.indices
                .flatMap { y ->
                    monster[y].indices.map { x ->
                        Coordinate(x, y)
                    }
                }
                .filter { monster[it.y][it.x] == '#' }
                .toSet())
    }

    fun solveFirst(tiles: String) =
        multiplyCorners(assemble(parseTiles(tiles)))

    fun solveSecond(tiles: String) =
        determineRoughness(assemble(parseTiles(tiles)))

    private fun parseTiles(tiles: String) = tiles.split("\n\n")
        .map { it.split("\n") }
        .map { Tile.of(it) }
        .toSet()

    private fun assemble(
        tiles: Set<Tile>,
        nextPosition: Coordinate = Coordinate(0, 0),
        sideLength: Int = sqrt(tiles.size),
        grid: Map<Coordinate, OrientedTile> = mapOf()
    ): List<Map<Coordinate, OrientedTile>> {
        if (tiles.isEmpty()) return listOf(grid)

        val gridList = mutableListOf<Map<Coordinate, OrientedTile>>()
        for (tile in tiles) {
            val newTiles = tiles - tile

            for (orientedTile in tile.orientedTiles) {
                if (fits(orientedTile, nextPosition, grid)) {
                    val newGrid = grid + (nextPosition to orientedTile)
                    gridList.addAll(
                        assemble(newTiles, nextPosition(nextPosition, sideLength), sideLength, newGrid)
                    )
                }
            }
        }

        return gridList
    }

    private fun fits(tile: OrientedTile, position: Coordinate, grid: Map<Coordinate, OrientedTile>): Boolean {
        if (position.x > 0) {
            val leftTile = grid[Coordinate(position.x - 1, position.y)]!!

            if (leftTile.right != tile.left) return false
        }

        if (position.y > 0) {
            val topTile = grid[Coordinate(position.x, position.y - 1)]!!

            if (topTile.bottom != tile.top) return false
        }

        return true
    }

    private fun nextPosition(position: Coordinate, sideLength: Int) =
        if (position.x + 1 == sideLength) Coordinate(0, position.y + 1)
        else Coordinate(position.x + 1, position.y)

    private fun multiplyCorners(grids: List<Map<Coordinate, OrientedTile>>, side: Int = sqrt(grids.first().size)) =
        grids.map { grid ->
            grid.filterKeys {
                it in setOf(
                    Coordinate(0, 0),
                    Coordinate(0, side - 1),
                    Coordinate(side - 1, 0),
                    Coordinate(side - 1, side - 1)
                )
            }
                .values
                .map { it.tileId.toLong() }
                .reduce(Long::times)
        }
            .distinct()
            .single()

    private fun determineRoughness(
        grids: List<Map<Coordinate, OrientedTile>>,
        side: Int = sqrt(grids.first().size),
        content: Int = grids.first().values.first().contents.size
    ) =
        grids.map { grid ->
            (0 until side * content)
                .map { y ->
                    (0 until side * content).map { x ->
                        grid[Coordinate(x / content, y / content)]!!.contents[y % content][x % content]
                    }
                }
        }
            .map { fullGrid ->
                (0..fullGrid.lastIndex - monster.height)
                    .flatMap { y ->
                        (0..fullGrid[y].lastIndex - monster.width).map { x ->
                            Coordinate(x, y)
                        }
                    }.filter { coord ->
                        monster.body.all { monster ->
                            fullGrid[monster.y + coord.y][monster.x + coord.x]
                        }
                    }
                    .count() to fullGrid
            }
            .single { it.first > 0 }
            .let {
                val nrMatches = it.first
                val fullGrid = it.second

                fullGrid.indices.flatMap { y -> fullGrid[y].indices.map { x -> Coordinate(x, y) } }
                    .filter { coord -> fullGrid[coord.y][coord.x] }
                    .count()
                    .toLong() - (nrMatches * monster.body.size)
            }

    private data class Tile(val id: Int, val orientedTiles: List<OrientedTile>) {

        companion object {
            private val regex = """^Tile (\d+):$""".toRegex()

            fun of(tile: List<String>): Tile {
                val id = regex.find(tile.head())!!.groupValues[1].toInt()
                val contents = tile.tail()

                val top = contents.indices
                    .map { contents[0][it] }
                    .map { readPixel(it) }
                val right = contents.indices
                    .map { contents[it][contents.lastIndex] }
                    .map { readPixel(it) }
                val bottom = contents.indices
                    .map { contents[contents.lastIndex][it] }
                    .map { readPixel(it) }
                val left = contents.indices
                    .map { contents[it][0] }
                    .map { readPixel(it) }
                val innerContent = (1 until contents.lastIndex)
                    .map { y ->
                        (1 until contents.lastIndex).map { x ->
                            readPixel(contents[y][x])
                        }
                    }

                val orientedTile = OrientedTile(id, top, right, bottom, left, innerContent)

                return Tile(
                    id,
                    listOf(
                        orientedTile,
                        orientedTile.rotate(),
                        orientedTile.rotate().rotate(),
                        orientedTile.rotate().rotate().rotate(),
                        orientedTile.flip(),
                        orientedTile.flip().rotate(),
                        orientedTile.flip().rotate().rotate(),
                        orientedTile.flip().rotate().rotate().rotate()
                    )
                )
            }

            private fun readPixel(pixel: Char) = when (pixel) {
                '.' -> false
                '#' -> true
                else -> error("Received invalid pixel: $pixel")
            }
        }
    }

    private data class OrientedTile(
        val tileId: Int,
        val top: List<Boolean>,
        val right: List<Boolean>,
        val bottom: List<Boolean>,
        val left: List<Boolean>,
        val contents: List<List<Boolean>>
    ) {
        fun rotate(): OrientedTile {
            val newContents = contents.indices
                .map { y ->
                    contents.indices.map { x ->
                        contents[contents.lastIndex - x][y]
                    }
                }
            return OrientedTile(tileId, left.reversed(), top, right.reversed(), bottom, newContents)
        }

        fun flip(): OrientedTile {
            val newContents = contents.indices
                .map { y ->
                    contents.indices.map { x ->
                        contents[y][contents.lastIndex - x]
                    }
                }
            return OrientedTile(tileId, top.reversed(), left, bottom.reversed(), right, newContents)
        }
    }

    private data class Coordinate(val x: Int, val y: Int)

    private data class Monster(val width: Int, val height: Int, val body: Set<Coordinate>)
}
