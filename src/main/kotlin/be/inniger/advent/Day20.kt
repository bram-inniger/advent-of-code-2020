package be.inniger.advent

import be.inniger.advent.util.head
import be.inniger.advent.util.sqrt
import be.inniger.advent.util.tail

class Day20 {

    fun solveFirst(tiles: String) =
        multiplyCorners(
            assemble(
                tiles.split("\n\n")
                    .map { it.split("\n") }
                    .map { Tile.of(it) }
                    .toSet()
            ))

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

                    if (gridList.isNotEmpty()) return gridList
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

    private fun multiplyCorners(
        grids: List<Map<Coordinate, OrientedTile>>,
        sideLength: Int = sqrt(grids[0].size)
    ) =
        grids.map { grid ->
            grid.filterKeys {
                it in setOf(
                    Coordinate(0, 0),
                    Coordinate(0, sideLength - 1),
                    Coordinate(sideLength - 1, 0),
                    Coordinate(sideLength - 1, sideLength - 1)
                )
            }
                .values
                .map { it.tileId.toLong() }
                .reduce(Long::times)
        }
            .distinct()
            .single()

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

                return Tile(
                    id,
                    listOf(
                        // rotated
                        OrientedTile(id, top, right, bottom, left),
                        OrientedTile(id, left.reversed(), top, right.reversed(), bottom),
                        OrientedTile(id, bottom.reversed(), left.reversed(), top.reversed(), right.reversed()),
                        OrientedTile(id, right, bottom.reversed(), left, top.reversed()),
                        // flipped + rotated
                        OrientedTile(id, top.reversed(), left, bottom.reversed(), right),
                        OrientedTile(id, right.reversed(), top.reversed(), left.reversed(), bottom.reversed()),
                        OrientedTile(id, bottom, right.reversed(), top, left.reversed()),
                        OrientedTile(id, left, bottom, right, top),
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
        val left: List<Boolean>
    )

    private data class Coordinate(val x: Int, val y: Int)
}
